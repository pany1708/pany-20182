package com.kingthy.ssh;

import com.kingthy.ssh.exception.SSHException;
import com.kingthy.ssh.utils.ConstUtils;
import com.kingthy.ssh.utils.IntegerUtil;
import com.kingthy.ssh.utils.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: 潘勇
 * @Description: SSH工具类
 * @Date: 2017/12/7 19:05
 */
public class SSHUtil
{

    public static String EMPTY_STRING = "";

    public static String[] EMPTY_STRING_ARRAY = new String[0];

    private static final Logger logger = LoggerFactory.getLogger(SSHUtil.class);

    //使用 @SSHTemplate 重构SSHUtil
    private final static SSHTemplate sshTemplate = new SSHTemplate();

    /**
     * @Author: 潘勇
     * @Description: SSH 方式登录远程主机，执行命令,方法内部会关闭所有资源，调用方无须关心。
     * @Date: 2017/12/7 19:05
     */
    public static String execute(String ip, int port, String username, String password,
        final String command)
        throws SSHException
    {

        if (StringUtil.isBlank(command))
        {
            return EMPTY_STRING;
        }
        port = IntegerUtil.defaultIfSmallerThan0(port, ConstUtils.SSH_PORT_DEFAULT);

        SSHTemplate.Result rst = sshTemplate.execute(ip, port, username, password, new SSHTemplate.SSHCallback()
        {
            @Override
            public SSHTemplate.Result call(SSHTemplate.SSHSession session)
            {
                return session.executeCommand(command);
            }
        });
        if (rst.isSuccess())
        {
            return rst.getResult();
        }
        return "";
    }

    /**
     * @Author: 潘勇
     * @Description: 远程文件复制
     * @Date: 2017/12/7 19:05
     */
    public static boolean scpFileToRemote(String ip, int port, String username,
        String password, final String localPath, final String remoteDir)
        throws SSHException
    {
        SSHTemplate.Result rst = sshTemplate.execute(ip, port, username, password, new SSHTemplate.SSHCallback()
        {
            @Override
            public SSHTemplate.Result call(SSHTemplate.SSHSession session)
            {
                return session.scpToDir(localPath, remoteDir, "0644");
            }
        });
        if (rst.isSuccess())
        {
            return true;
        }
        if (rst.getExcetion() != null)
        {
            throw new SSHException(rst.getExcetion());
        }
        return false;
    }

    /**
     * @Author: 潘勇
     * @Description: 重载，使用默认端口、用户名和密码
     * @Date: 2017/12/7 19:06
     */
    public static boolean scpFileToRemote(String ip, String localPath, String remoteDir)
        throws SSHException
    {
        int sshPort = SSHUtil.getSshPort(ip);
        return scpFileToRemote(ip, sshPort, ConstUtils.USERNAME, ConstUtils.PASSWORD, localPath, remoteDir);
    }

    /**
     * @Author: 潘勇
     * @Description: 重载，使用默认端口、用户名和密码
     * @Date: 2017/12/7 19:06
     */
    public static String execute(String ip, String cmd)
        throws SSHException
    {
        int sshPort = SSHUtil.getSshPort(ip);
        return execute(ip, sshPort, ConstUtils.USERNAME, ConstUtils.PASSWORD, cmd);
    }

    /**
     * @Author: 潘勇
     * @Description: 查看机器ip上的端口port是否已被占用；
     * @Date: 2017/12/7 19:06
     */
    public static boolean isPortUsed(String ip, int port)
        throws SSHException
    {
        /**
         * 执行ps命令，查看端口，以确认刚才执行的shell命令是否成功，返回一般是这样的：
         *  root     12510 12368  0 14:34 pts/0    00:00:00 redis-server *:6379
         */
        String psCmd = "/bin/ps -ef | grep %s | grep -v grep";
        psCmd = String.format(psCmd, port);
        String psResponse = execute(ip, psCmd);
        boolean isUsed = false;

        if (StringUtils.isNotBlank(psResponse))
        {
            String[] resultArr = psResponse.split(System.lineSeparator());
            for (String resultLine : resultArr)
            {
                if (resultLine.contains(String.valueOf(port)))
                {
                    isUsed = true;
                    break;
                }
            }
        }
        return isUsed;
    }

    /**
     * @Author: 潘勇
     * @Description: 通过ip来判断ssh端口
     * @Date: 2017/12/7 19:06
     */
    public static int getSshPort(String ip)
    {
        /**
         * 如果ssh默认端口不是22,请自行实现该逻辑
         */
        return ConstUtils.SSH_PORT_DEFAULT;
    }

}
