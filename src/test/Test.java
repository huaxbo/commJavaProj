package test;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import org.apache.log4j.Logger;
import org.codehaus.xfire.client.Client;

import com.automic.gxpt.ws.rev.intr.ShareDataPortTypeProxy;

public class Test {

	private static Logger log = Logger.getLogger(Test.class);
	
	private void test(){
		
		System.out.println("hello test !!!");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			Client client = new Client(new URL("http://localhost:6004/gxpt/CXFService/shareData?wsdl"));
			log.info("11111");
			client.invoke("receive", new Object[]{"1","hello"});
			log.info("22222");
			client.invoke("receiveRemoteData", new Object[]{"hello2"});
			log.info("33333");
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		// TODO Auto-generated method stub
		/*ShareDataPortTypeProxy pro = new ShareDataPortTypeProxy();
		
		try {
			log.info("11111");
			pro.receive("1", "11");		
			log.info("22222");
			pro.receiveRemoteData("22");
			log.info("3333333");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}*/
		
	}

}
