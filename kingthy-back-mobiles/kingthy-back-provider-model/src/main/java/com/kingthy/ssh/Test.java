package com.kingthy.ssh;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import com.kingthy.ssh.exception.SSHException;
import com.kingthy.ssh.utils.ConstUtils;
import com.kingthy.ssh.utils.IntegerUtil;
import org.apache.tools.ant.util.Watchdog;
import org.springframework.util.StopWatch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Author 潘勇
 * @Data 2017/12/6 16:32.
 */
public class Test
{

    private static final int CONNCET_TIMEOUT = 6000;

    private static final int OP_TIMEOUT = 12000;

    public static Connection getConnection(String ip, int port,
        String username, String password)
        throws Exception
    {
        Connection conn = new Connection(ip, port);
        conn.connect(null, CONNCET_TIMEOUT, CONNCET_TIMEOUT);
        boolean isAuthenticated = conn.authenticateWithPassword(username, password);
        if (isAuthenticated == false)
        {
            throw new Exception("SSH authentication failed with [ userName: " +
                username + ", password: " + password + "]");
        }
        return conn;
    }

    public static void main(String[] args)
    {
        String ip = "192.168.7.191";
        Integer sshPort = 22;
//        String javaPidCmd = "ps -ef | grep java | grep -v 'grep'";
//        String javaPidCmd = "/data/service/show-busiest-java-threads.sh 16662";
//        String javaPidCmd = "vmstat 1 10";
//        String javaPidCmd = "cd /data && scp bluk.sh root@192.168.7.198:/data";
//        String javaPidCmd =
//        "cd /bodyRecon &&  /bodyRecon/ReconBody /bodyRecon/data bodyPointCloudMLS.txt BodyJointPointTraned.txt /data/service/970710615725181951511852206344/pany2021.obj";
        String javaPidCmd =
            "cd /bodyRecon &&  ./run.sh ./bodyPointCloudMLS.txt ./BodyJointPointTraned.txt body1207.2.obj";
        try

        {
            Connection getConnection = getConnection(ip, sshPort, "zenmme", "123456");
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            getConnection.openSession();
//            String result = SSHUtil.execute(ip, sshPort, "zenmme", "123456", javaPidCmd);
//            System.out.printf("redisProcessStr: " + redisProcessStr);
            boolean result = SSHUtil.isPortUsed("192.168.1.191", 3306);
//            SSHTemplate sshTemplate = new SSHTemplate();
//            SSHTemplate.SSHSession sshSession = sshTemplate.new SSHSession(getConnection, "");
//            SSHTemplate.Result result = sshSession.executeCommand(javaPidCmd);
            stopWatch.stop();
            System.out.printf("total time: " + stopWatch.getTotalTimeSeconds());
            System.out.printf("result:" + result);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}

