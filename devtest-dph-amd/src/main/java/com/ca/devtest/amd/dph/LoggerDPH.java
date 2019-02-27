package com.ca.devtest.amd.dph;


import com.itko.lisa.VSE;
import com.itko.lisa.test.TestExec;
import com.itko.lisa.vse.stateful.model.Request;
import com.itko.lisa.vse.stateful.model.Transaction;
import com.itko.lisa.vse.stateful.model.TransientResponse;
import com.itko.lisa.vse.stateful.protocol.DataProtocol;
import com.itko.util.GUID;

public class LoggerDPH extends DataProtocol {

	private static final String GUID_KEY="lisa.vse.transaction.guid";
	private static final String MATCHED_TRANSACTION_PROPERTY="lisa.vse.matched.transaction";
	

	public LoggerDPH() {
		super();
	}

	
	
	@Override
	public void updateRequest(TestExec testExec, Request request) {
		String guid=GUID.newGUID();	
		String reqBody = request.getBodyAsString();
		VSE.info(testExec, "Request uid: "+guid, request.toJSONString(true));
		// here you should parse the reqBody message and pull out the operation name and the data for the fields you are interested in
		testExec.setStateValue(GUID_KEY, guid);
	}
	

	
	

	@Override
	public void updateResponse(TestExec testExec, TransientResponse transientResponse) {
		
		String guid= (String)testExec.getStateValue(GUID_KEY);
		Transaction transaction =(Transaction)testExec.getStateObject(MATCHED_TRANSACTION_PROPERTY);
		
		VSE.info(testExec, "Response uid: "+guid, transientResponse.getBodyAsString());
		VSE.info(testExec, "Transaction uid: "+guid, transaction.toJSONString());
		
		
	}
}
