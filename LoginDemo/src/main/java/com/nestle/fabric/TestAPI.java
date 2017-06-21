package com.nestle.fabric;

public class TestAPI {

	public static final String vpUrl = "https://33c650b118044c24a69dd093650b726d-vp0.us.blockchain.ibm.com:5002";
	// public static final String chainCodeName =
	// "c427b56ab5b4ec53283608cf5d34d9fd638ff438d00bec5017a71e8b0bbec01109b76315b8fac973f833ccb7723c20146244cc9d57bcf7ad408fab147ec2ec6e";
	public static final String chainCodeName = "ebcb2f7f9fd4af94df68d4c701b19168716fef085ef7e11d1cf094fa611436691b1693fdf0fb5475b602596bf3fd09865a8b8d4b091c6714c607e7d5c9b636b1";
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

}