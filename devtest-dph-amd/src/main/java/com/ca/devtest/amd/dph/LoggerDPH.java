package com.ca.devtest.amd.dph;


import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.eclipse.jetty.util.UrlEncoded;

import com.itko.lisa.VSE;
import com.itko.lisa.coordinator.VirtualService;
import com.itko.lisa.test.TestExec;
import com.itko.lisa.vse.stateful.model.Request;
import com.itko.lisa.vse.stateful.model.Transaction;
import com.itko.lisa.vse.stateful.model.TransientResponse;
import com.itko.lisa.vse.stateful.protocol.DataProtocol;
import com.itko.util.GUID;

public class LoggerDPH extends DataProtocol {

	private static final String INBOUND="lisa.vse.transaction.inbound";
	private static final String MATCHED_TRANSACTION_PROPERTY="lisa.vse.matched.transaction";
	

	public LoggerDPH() {
		super();
	}

	
	
	@Override
	public void updateRequest(TestExec testExec, Request request) {
			

		// here you should parse the reqBody message and pull out the operation name and the data for the fields you are interested in
		Map<String,String> data= new HashMap<String, String>();
		data.put("Request_timestamp", String.valueOf(System.currentTimeMillis()));
		data.put("Request_json", request.toString());
		
		
		testExec.setStateValue(INBOUND, data);
		
		
	}
	

	
	

	@Override
	public void updateResponse(TestExec testExec, TransientResponse transientResponse) {
		
		try {
			
		
		Transaction transaction =(Transaction)testExec.getStateObject(MATCHED_TRANSACTION_PROPERTY);
		VirtualService  virtualService=(VirtualService)testExec.getStateObject("LISA_VIRTUAL_SERVICE");
		
		String requestJSON= transaction.getRequest().toJSONString(false);
		
		String transactionJson=transaction.toJSONString();
		
		String recordingName=virtualService.getName();
		
		String uuid=virtualService.getGroupTag();
		String vseName=virtualService.getVirtualEnvironment().getName();
		
		
				
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally {
			
		}
	
		
		
	}
}
