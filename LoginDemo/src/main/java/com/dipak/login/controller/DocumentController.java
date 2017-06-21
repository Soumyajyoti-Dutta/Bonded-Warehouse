package com.dipak.login.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.box.sdk.BoxAPIConnection;
import com.dipak.login.dao.RejectionCodeDao;
import com.dipak.login.model.BoxUtil;
import com.dipak.login.model.Document;
import com.dipak.login.model.Item;
import com.dipak.login.model.RejectionCode;
import com.dipak.login.model.Status;
import com.dipak.login.model.Transaction;
import com.dipak.login.service.DocumentService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.nestle.fabric.TestAPI;

@Controller
public class DocumentController {

	@Autowired
	private DocumentService documentService;
	@Autowired
	private RejectionCodeDao rejectionCodeDao;

	@GetMapping("initiated")
	public String fetchInitiatedDocs(Model model, HttpSession session) {

		System.out.println("Working!!!!!!!!");
		if (session.getAttribute("userRole").equals("warehouse")) {
			List<Document> docList = documentService.searchDocumentByStatus("Initial");
			model.addAttribute("documents", docList);

			Status status = documentService.countStatus();
			model.addAttribute("status", status);

			return "warehouse";
		} else if (session.getAttribute("userRole").equals("thirdparty")) {
			List<Document> docList = documentService.searchDocumentByStatus("Initial");
			model.addAttribute("documents", docList);

			Status status = documentService.countStatus();
			model.addAttribute("status", status);

			List<RejectionCode> rejections = rejectionCodeDao.getAllRejection();
			model.addAttribute("rejections", rejections);

			return "thirdparty";
		} else {
			List<Document> docList = documentService.searchDocumentByStatus("Initial");
			model.addAttribute("documents", docList);

			Status status = documentService.countStatus();
			model.addAttribute("status", status);

			List<RejectionCode> rejections = rejectionCodeDao.getAllRejection();
			model.addAttribute("rejections", rejections);

			return "custom";
		}
	}

	@GetMapping("verified")
	public String fetchVerifiedDocs(Model model, HttpSession session) {

		System.out.println("Working!!!!!!!!");
		if (session.getAttribute("userRole").equals("warehouse")) {
			List<Document> docList = documentService.searchDocumentByStatus("Verified");
			model.addAttribute("documents", docList);

			Status status = documentService.countStatus();
			model.addAttribute("status", status);

			return "warehouse";
		} else if (session.getAttribute("userRole").equals("thirdparty")) {
			List<Document> docList = documentService.searchDocumentByStatus("Verified");
			model.addAttribute("documents", docList);

			Status status = documentService.countStatus();
			model.addAttribute("status", status);

			List<RejectionCode> rejections = rejectionCodeDao.getAllRejection();
			model.addAttribute("rejections", rejections);

			return "thirdparty";
		} else {
			List<Document> docList = documentService.searchDocumentByStatus("Verified");
			model.addAttribute("documents", docList);

			Status status = documentService.countStatus();
			model.addAttribute("status", status);

			List<RejectionCode> rejections = rejectionCodeDao.getAllRejection();
			model.addAttribute("rejections", rejections);

			return "custom";
		}
	}

	@GetMapping("processed")
	public String fetchProcessedDocs(Model model, HttpSession session) {
		System.out.println("Working!!!!!!!!");
		if (session.getAttribute("userRole").equals("warehouse")) {
			List<Document> docList = documentService.searchDocumentByStatus("Processed");
			model.addAttribute("documents", docList);

			Status status = documentService.countStatus();
			model.addAttribute("status", status);

			return "warehouse";
		} else if (session.getAttribute("userRole").equals("thirdparty")) {
			List<Document> docList = documentService.searchDocumentByStatus("Processed");
			model.addAttribute("documents", docList);

			Status status = documentService.countStatus();
			model.addAttribute("status", status);

			List<RejectionCode> rejections = rejectionCodeDao.getAllRejection();
			model.addAttribute("rejections", rejections);

			return "thirdparty";
		} else {
			List<Document> docList = documentService.searchDocumentByStatus("Processed");
			model.addAttribute("documents", docList);

			Status status = documentService.countStatus();
			model.addAttribute("status", status);

			List<RejectionCode> rejections = rejectionCodeDao.getAllRejection();
			model.addAttribute("rejections", rejections);

			return "custom";
		}
	}

	@GetMapping("rejected")
	public String fetchRejectedDocs(Model model, HttpSession session) {
		System.out.println("Working!!!!!!!!");
		if (session.getAttribute("userRole").equals("warehouse")) {
			// Map<String, String> rejectCodeIDMap = new HashMap<String,
			// String>();
			List<RejectionCode> rejections = rejectionCodeDao.getAllRejection();
			// for (RejectionCode rejectionCode : rejections) {
			// rejectCodeIDMap.put(rejectionCode.getRejection_description(),
			// String.valueOf(rejectionCode.getRejection_Id()));
			// }
			model.addAttribute("rejections", rejections);
			List<Document> docList = documentService.searchDocumentByStatus("Rejected");
			model.addAttribute("documents", docList);
			List<String> updatedByList = new ArrayList<String>();
			for (Document document : docList) {
				String docId = document.getDocumentId();
				String doctrxHistList = TestAPI.viewDocumentTrxHistory(docId);
				// System.out.println("HISTORY : : :" + doctrxHist);
				Gson myGson = new Gson();
				JsonParser jsonParser = new JsonParser();
				JsonArray docTrxArray = jsonParser.parse(doctrxHistList).getAsJsonArray();
				// Transaction transaction = new Transaction();
				for (JsonElement docTrx : docTrxArray) {
					Transaction transaction = myGson.fromJson(docTrx, Transaction.class);
					if (transaction.getStatus().equals("Rejected")) {
						updatedByList.add(transaction.getUpdatedBy());
					}
				}

			}
			// System.out.println("REJECTION : : :" + updatedByList);
			Status status = documentService.countStatus();
			model.addAttribute("status", status);
			model.addAttribute("updatedByList", updatedByList);

			return "warehouse";
		} else if (session.getAttribute("userRole").equals("thirdparty")) {
			List<Document> docList = documentService.searchDocumentByStatus("Rejected");
			model.addAttribute("documents", docList);

			Status status = documentService.countStatus();
			model.addAttribute("status", status);

			List<String> updatedByList = new ArrayList<String>();
			for (Document document : docList) {
				String docId = document.getDocumentId();
				String doctrxHistList = TestAPI.viewDocumentTrxHistory(docId);
				Gson myGson = new Gson();
				JsonParser jsonParser = new JsonParser();
				JsonArray docTrxArray = jsonParser.parse(doctrxHistList).getAsJsonArray();
				for (JsonElement docTrx : docTrxArray) {
					Transaction transaction = myGson.fromJson(docTrx, Transaction.class);
					if (transaction.getStatus().equals("Rejected")) {
						updatedByList.add(transaction.getUpdatedBy());
					}
				}

			}
			model.addAttribute("updatedByList", updatedByList);

			return "thirdparty";
		} else {
			List<Document> docList = documentService.searchDocumentByStatus("Rejected");
			model.addAttribute("documents", docList);

			Status status = documentService.countStatus();
			model.addAttribute("status", status);

			List<String> updatedByList = new ArrayList<String>();
			for (Document document : docList) {
				String docId = document.getDocumentId();
				String doctrxHistList = TestAPI.viewDocumentTrxHistory(docId);
				Gson myGson = new Gson();
				JsonParser jsonParser = new JsonParser();
				JsonArray docTrxArray = jsonParser.parse(doctrxHistList).getAsJsonArray();
				for (JsonElement docTrx : docTrxArray) {
					Transaction transaction = myGson.fromJson(docTrx, Transaction.class);
					if (transaction.getStatus().equals("Rejected")) {
						updatedByList.add(transaction.getUpdatedBy());
					}
				}

			}
			model.addAttribute("updatedByList", updatedByList);

			return "custom";
		}
	}

	@GetMapping("approved")
	public String fetchApprovedDocs(Model model, HttpSession session) {
		System.out.println("Working!!!!!!!!");
		if (session.getAttribute("userRole").equals("warehouse")) {
			List<Document> docList = documentService.searchDocumentByStatus("Approved");
			model.addAttribute("documents", docList);

			Status status = documentService.countStatus();
			model.addAttribute("status", status);

			return "warehouse";
		} else if (session.getAttribute("userRole").equals("thirdparty")) {
			List<Document> docList = documentService.searchDocumentByStatus("Approved");
			model.addAttribute("documents", docList);

			Status status = documentService.countStatus();
			model.addAttribute("status", status);

			return "thirdparty";
		} else {
			List<Document> docList = documentService.searchDocumentByStatus("Approved");
			model.addAttribute("documents", docList);

			Status status = documentService.countStatus();
			model.addAttribute("status", status);

			return "custom";
		}
	}

	@GetMapping("received")
	public String fetchReceivedDocs(Model model, HttpSession session) {
		System.out.println("Working!!!!!!!!");
		if (session.getAttribute("userRole").equals("warehouse")) {
			List<Document> docList = documentService.searchDocumentByStatus("Received");
			model.addAttribute("documents", docList);

			Status status = documentService.countStatus();
			model.addAttribute("status", status);

			return "warehouse";
		} else if (session.getAttribute("userRole").equals("thirdparty")) {
			List<Document> docList = documentService.searchDocumentByStatus("Received");
			model.addAttribute("documents", docList);

			Status status = documentService.countStatus();
			model.addAttribute("status", status);

			return "thirdparty";
		} else {
			List<Document> docList = documentService.searchDocumentByStatus("Received");
			model.addAttribute("documents", docList);

			Status status = documentService.countStatus();
			model.addAttribute("status", status);

			return "custom";
		}
	}

	@PostMapping("updateStatus")
	public String updateStatusDocs(Model model, @RequestParam("id") String id, @RequestParam("status") String status,
			@RequestParam("reasonCode") String reasonCode, HttpServletRequest request, HttpSession session) {
		String updatedBy = request.getParameter("username");
		String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
		String statusUpdate = documentService.updateStatus(id, status, timeStamp, updatedBy, reasonCode);
		if (session.getAttribute("userRole").equals("warehouse")) {
			System.out.println("DocumentController :: updateStatusDocs :: id = " + id + "status = " + status);
			if (statusUpdate == "success") {
				Status currentStatus = documentService.countStatus();
				model.addAttribute("statusUpdate", "document successfully updated!!");
				model.addAttribute("status", currentStatus);
				model.addAttribute("id", id);
				return "warehouse";
			} else {
				model.addAttribute("statusUpdate", "document update failed!!");
				return "warehouse";
			}
		} else if (session.getAttribute("userRole").equals("thirdparty")) {
			System.out.println("DocumentController :: updateStatusDocs :: id = " + id + "status = " + status);
			if (statusUpdate == "success") {
				System.out.println("User ===" + updatedBy);
				Status currentStatus = documentService.countStatus();
				model.addAttribute("statusUpdate", "document successfully updated!!");
				model.addAttribute("status", currentStatus);
				model.addAttribute("id", id);
				return "thirdparty";
			} else {
				model.addAttribute("statusUpdate", "document update failed!!");
				return "thirdparty";
			}
		} else {
			System.out.println("DocumentController :: updateStatusDocs :: id = " + id + "status = " + status);
			if (statusUpdate == "success") {
				System.out.println("User ===" + updatedBy);
				Status currentStatus = documentService.countStatus();
				model.addAttribute("statusUpdate", "document successfully updated!!");
				model.addAttribute("status", currentStatus);
				model.addAttribute("id", id);
				return "custom";
			} else {
				model.addAttribute("statusUpdate", "document update failed!!");
				return "custom";
			}
		}

	}

	@GetMapping("createNewDoc")
	public String createCBPage(Model model, HttpSession session) {
		session.getAttribute("username");
		return "createNewDoc";
	}

	@GetMapping("updateDoc")
	public String updateCBPage(Model model, HttpSession session, HttpServletRequest request,
			@RequestParam("id") String id) {
		session.getAttribute("username");
		// String docuID = request.getParameter("searchDocId");
		List<Document> docsbyId = documentService.searchDocumentWithID(id);
		List<Item> items = documentService.getItemListByDocumentID(id);
		model.addAttribute("docsbyId", docsbyId);
		model.addAttribute("items", items);
		model.addAttribute("documentId", id);
		System.out.println("New Page!!!!");
		return "updateDoc";
	}

	@GetMapping("searchDocument")
	public String searchDocPage(Model model, HttpSession session) {
		session.getAttribute("username");
		session.getAttribute("userRole");
		return "searchDocument";
	}

	@GetMapping("openDoc")
	public void opebDocs(Model model, HttpServletRequest request, HttpSession session, HttpServletResponse response)
			throws Exception {

		if (request.getParameter("code") == null) {
			List<String> arg3 = new ArrayList<String>();
			URI url = null;
			try {
				String docId = request.getParameter("docId");
				String serverURL = request.getRequestURL().toString();
				// System.out.println("serverURL ===" + serverURL);
				String documentURL = serverURL + "?docId=";
				// System.out.println("documentURL===" + documentURL);
				url = new URI(documentURL + docId);
				// url = new
				// URI("http://localhost:8080/logindemo/openDoc?docId=" +
				// docId);
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			URL authURL = BoxAPIConnection.getAuthorizationURL("rl0okd37q0nm7ssnbb5x05k0efq1mt5i", url, "authenticated",
					arg3);
			String urlStr = authURL.toString();
			System.out.println("AUTHORIZATION URL ::::::: " + urlStr);
			response.sendRedirect(urlStr);
		} else {
			String code = request.getParameter("code");
			BoxAPIConnection api = new BoxAPIConnection("rl0okd37q0nm7ssnbb5x05k0efq1mt5i",
					"WONwEVxGlyGbzo0juyHftfj1rW0VLHc5", code);
			String docId = request.getParameter("docId");
			String fileName = BoxUtil.downloadFile(api, docId);
			System.out.println("docId===" + docId + "aND dOCnAME :::: " + fileName);
			String downloadName = "";
			String downloadFileHash = "";
			String blockchainFileHash = "";
			try {
				MessageDigest md5Digest = MessageDigest.getInstance("MD5");
				downloadName = System.getProperty("java.io.tmpdir") + fileName;
				downloadFileHash = BoxUtil.getFileChecksum(md5Digest, downloadName);
				String getFileDetailsFromBlockchain = TestAPI.getDetailsByDocumetId(docId);
				getFileDetailsFromBlockchain = getFileDetailsFromBlockchain.replace("[", "");
				getFileDetailsFromBlockchain = getFileDetailsFromBlockchain.replace("]", "");
				Gson gson = new Gson();
				Document document = gson.fromJson(getFileDetailsFromBlockchain, Document.class);
				blockchainFileHash = document.getDocHash();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (downloadFileHash.equals(blockchainFileHash)) {

				response.setContentType("text/html;charset=UTF-8");
				ServletOutputStream outs = response.getOutputStream();
				response.setContentType("application/pdf"); // MIME type for pdf
															// doc
				File file = new File(System.getProperty("java.io.tmpdir") + fileName);
				response.setHeader("Content-disposition", "inline; filename=" + fileName);
				BufferedInputStream bis = null;
				BufferedOutputStream bos = null;
				try {

					InputStream isr = new FileInputStream(file);
					bis = new BufferedInputStream(isr);
					bos = new BufferedOutputStream(outs);
					byte[] buff = new byte[2048];
					int bytesRead;
					// Simple read/write loop.
					while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
						bos.write(buff, 0, bytesRead);
					}

				} catch (Exception e) {
					System.out.println("Exception ----- Message ---" + e);
				} finally {
					if (bis != null)
						bis.close();
					if (bos != null)
						bos.close();
					File tempFile = new File(downloadName);
					tempFile.delete();
					System.out.println("File deleted successfully");
				}

			}

			else
				System.out.println("File altered in Content management server");
		}

	}

	@GetMapping("showTrxHistory")
	public String showTrxHistory(Model model, HttpSession session, HttpServletRequest request) {
		String docuID = request.getParameter("searchDocId");
		List<Transaction> trxHistory = documentService.searchDocumentByID(docuID);
		String statusList = "";
		for (Transaction transaction : trxHistory) {
			statusList += transaction.getUpdatedBy() + "_";
			statusList += transaction.getStatus() + ",";
		}
		List<Document> docsbyId = documentService.searchDocumentWithID(docuID);
		String lastStatus = "";
		for (Document document : docsbyId) {
			lastStatus = document.getStatus();
		}
		model.addAttribute("docsbyId", docsbyId);
		model.addAttribute("trxHistory", trxHistory);
		model.addAttribute("statusList", statusList);
		model.addAttribute("lastStatus", lastStatus);
		return "transactionHistory";
	}
}
