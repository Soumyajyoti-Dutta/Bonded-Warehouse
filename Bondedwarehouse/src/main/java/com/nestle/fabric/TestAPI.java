package com.nestle.fabric;

public class TestAPI {

	public static final String vpUrl = "https://33c650b118044c24a69dd093650b726d-vp0.us.blockchain.ibm.com:5002";
	// 47b76262387216ef8bb88a343294ac08cd57fa3992a040c1ab6a757ca87a68d6e560a2211a51f8add878e89a3d3ad45792040c8e8f1c634f867d882213693b53
	public static final String chainCodeName = "1ed5b8233297da2a7fce9a410e5bb08910d15126392c272f7a70d5de56cd21f7c7996a5d179aff16515ab9b59abdae1373051743f637cdb9a23f42314594a4eb";
	public static final String user = "admin";
	public static final String userSecret = "e0e9a6251b";

	public String interactWithLedger(String action, String documentId, String status, String timeStamp,
			String updatedBy, String reasonCode) {

		String response = "";

		APICallUtil apiCallUtil = new APICallUtil(vpUrl, user, userSecret);
		if (action == "update") {
			updateDocumentStatus(apiCallUtil, documentId, status, timeStamp, updatedBy, reasonCode);
			response = "Document status updated successfully";
		} else if (action == "recordCount") {
			response = countDocumentByStatus(apiCallUtil, status);
			return response;
		} else
			response = searchDocumentByStatus(apiCallUtil, status);
		// System.out.println(apiCallUtil.register());
		return response;
	}

	public static String createDocument(String documentId, String source, String destination, String timeStamp,
			String updatedBy, String documentHash, String deliveryDate, String deliveryNo, String custPO,
			String truckId, String customLocation, String sourceLatLong, String destnationLatLong,
			String customLatLong) {
		APICallUtil apiCallUtil = new APICallUtil(vpUrl, user, userSecret);
		String[] args = { documentId, source, destination, timeStamp, updatedBy, documentHash, deliveryDate, deliveryNo,
				custPO, truckId, customLocation, sourceLatLong, destnationLatLong, customLatLong };
		HyperLedgerRequest hyperLedgerRequest = new HyperLedgerRequest();
		hyperLedgerRequest.setMethod("invoke");
		hyperLedgerRequest.setCallFunction("createDocument");
		hyperLedgerRequest.setChainCodeName(chainCodeName);
		hyperLedgerRequest.setFunctionArgs(args);
		hyperLedgerRequest.setSecureContext("admin");
		HyperLedgerResponse response = apiCallUtil.invokeMethod(hyperLedgerRequest);
		String resp = response.getMessage();
		return resp;
	}

	public static String createDocumentItemList(String documentId, String itemName, String itemQuantity,
			String itemDesc, String itemVolume) {
		APICallUtil apiCallUtil = new APICallUtil(vpUrl, user, userSecret);
		String[] args = { documentId, itemName, itemQuantity, itemDesc, itemVolume };
		HyperLedgerRequest hyperLedgerRequest = new HyperLedgerRequest();
		hyperLedgerRequest.setMethod("invoke");
		hyperLedgerRequest.setCallFunction("insertItemList");
		hyperLedgerRequest.setChainCodeName(chainCodeName);
		hyperLedgerRequest.setFunctionArgs(args);
		hyperLedgerRequest.setSecureContext("admin");
		HyperLedgerResponse response = apiCallUtil.invokeMethod(hyperLedgerRequest);
		String resp = response.getMessage();
		return resp;
	}

	public static String searchDocumentByStatus(APICallUtil apiCallUtil, String key) {
		String[] args = { key };
		HyperLedgerRequest hyperLedgerRequest = new HyperLedgerRequest();
		hyperLedgerRequest.setMethod("query");
		hyperLedgerRequest.setCallFunction("viewDocumentsByStatus");
		hyperLedgerRequest.setChainCodeName(chainCodeName);
		hyperLedgerRequest.setFunctionArgs(args);
		hyperLedgerRequest.setSecureContext("admin");
		HyperLedgerResponse response = apiCallUtil.invokeMethod(hyperLedgerRequest);
		String details = response.getMessage();
		return details;
	}

	public static String countDocumentByStatus(APICallUtil apiCallUtil, String key) {
		String[] args = { key };
		HyperLedgerRequest hyperLedgerRequest = new HyperLedgerRequest();
		hyperLedgerRequest.setMethod("query");
		hyperLedgerRequest.setCallFunction("countDocumentsByStatus");
		hyperLedgerRequest.setChainCodeName(chainCodeName);
		hyperLedgerRequest.setFunctionArgs(args);
		hyperLedgerRequest.setSecureContext("admin");
		HyperLedgerResponse response = apiCallUtil.invokeMethod(hyperLedgerRequest);
		String details = response.getMessage();
		String totalCount = details.replace("\"", "");
		return totalCount;
	}

	public static void updateDocumentStatus(APICallUtil apiCallUtil, String documentId, String status, String timeStamp,
			String updatedBy, String reasonCode) {
		String[] args = { documentId, status, timeStamp, updatedBy, reasonCode };
		HyperLedgerRequest hyperLedgerRequest = new HyperLedgerRequest();
		hyperLedgerRequest.setMethod("invoke");
		hyperLedgerRequest.setCallFunction("updateDocumentStatus");
		hyperLedgerRequest.setChainCodeName(chainCodeName);
		hyperLedgerRequest.setFunctionArgs(args);
		hyperLedgerRequest.setSecureContext("admin");
		HyperLedgerResponse response = apiCallUtil.invokeMethod(hyperLedgerRequest);
		System.out.println(response.getMessage());
	}

	public static String viewDocumentTrxHistory(String documentId) {
		APICallUtil apiCallUtil = new APICallUtil(vpUrl, user, userSecret);
		String[] args = { documentId };
		HyperLedgerRequest hyperLedgerRequest = new HyperLedgerRequest();
		hyperLedgerRequest.setMethod("query");
		hyperLedgerRequest.setCallFunction("viewDocumentTransactionHistory");
		hyperLedgerRequest.setChainCodeName(chainCodeName);
		hyperLedgerRequest.setFunctionArgs(args);
		hyperLedgerRequest.setSecureContext("admin");
		HyperLedgerResponse response = apiCallUtil.invokeMethod(hyperLedgerRequest);
		String details = response.getMessage();
		return details;
	}

	public static String getDetailsByDocumetId(String documentId) {
		APICallUtil apiCallUtil = new APICallUtil(vpUrl, user, userSecret);
		String[] args = { documentId };
		HyperLedgerRequest hyperLedgerRequest = new HyperLedgerRequest();
		hyperLedgerRequest.setMethod("query");
		hyperLedgerRequest.setCallFunction("viewDetailsByDocId");
		hyperLedgerRequest.setChainCodeName(chainCodeName);
		hyperLedgerRequest.setFunctionArgs(args);
		hyperLedgerRequest.setSecureContext("admin");
		HyperLedgerResponse response = apiCallUtil.invokeMethod(hyperLedgerRequest);
		String details = response.getMessage();
		return details;
	}

	public static String getItemListByDocumetId(String documentId) {
		APICallUtil apiCallUtil = new APICallUtil(vpUrl, user, userSecret);
		String[] args = { documentId };
		HyperLedgerRequest hyperLedgerRequest = new HyperLedgerRequest();
		hyperLedgerRequest.setMethod("query");
		hyperLedgerRequest.setCallFunction("viewItemListByDocumentId");
		hyperLedgerRequest.setChainCodeName(chainCodeName);
		hyperLedgerRequest.setFunctionArgs(args);
		hyperLedgerRequest.setSecureContext("admin");
		HyperLedgerResponse response = apiCallUtil.invokeMethod(hyperLedgerRequest);
		String details = response.getMessage();
		return details;
	}

	public static String updateRejectedDocument(String documentId, String source, String destination,
			String deliveryDate, String deliveryNo, String custPO, String documentHash, String timeStamp,
			String updatedBy, String truckId, String customLocation, String sourceLatLong, String destnationLatLong,
			String customLatLong) {
		APICallUtil apiCallUtil = new APICallUtil(vpUrl, user, userSecret);
		String[] args = { documentId, source, destination, deliveryDate, deliveryNo, custPO, documentHash, timeStamp,
				updatedBy, truckId, customLocation, sourceLatLong, destnationLatLong, customLatLong };
		HyperLedgerRequest hyperLedgerRequest = new HyperLedgerRequest();
		hyperLedgerRequest.setMethod("invoke");
		hyperLedgerRequest.setCallFunction("updateRejectedDocument");
		hyperLedgerRequest.setChainCodeName(chainCodeName);
		hyperLedgerRequest.setFunctionArgs(args);
		hyperLedgerRequest.setSecureContext("admin");
		HyperLedgerResponse response = apiCallUtil.invokeMethod(hyperLedgerRequest);
		String resp = response.getMessage();
		return resp;
	}

	public static String gethighPriorityDocumentByStatus(String flagValue) {
		APICallUtil apiCallUtil = new APICallUtil(vpUrl, user, userSecret);
		String[] args = { flagValue };
		HyperLedgerRequest hyperLedgerRequest = new HyperLedgerRequest();
		hyperLedgerRequest.setMethod("query");
		hyperLedgerRequest.setCallFunction("viewAllHighPriorityDocuments");
		hyperLedgerRequest.setChainCodeName(chainCodeName);
		hyperLedgerRequest.setFunctionArgs(args);
		hyperLedgerRequest.setSecureContext("admin");
		HyperLedgerResponse response = apiCallUtil.invokeMethod(hyperLedgerRequest);
		String details = response.getMessage();
		return details;
	}
	
	public static String viewTruckDetailsByDocId(String documentId) {
		APICallUtil apiCallUtil = new APICallUtil(vpUrl, user, userSecret);
		String[] args = { documentId };
		HyperLedgerRequest hyperLedgerRequest = new HyperLedgerRequest();
		hyperLedgerRequest.setMethod("query");
		hyperLedgerRequest.setCallFunction("viewTruckDetailsByDocId");
		hyperLedgerRequest.setChainCodeName(chainCodeName);
		hyperLedgerRequest.setFunctionArgs(args);
		hyperLedgerRequest.setSecureContext("admin");
		HyperLedgerResponse response = apiCallUtil.invokeMethod(hyperLedgerRequest);
		String details = response.getMessage();
		return details;
	} 

}