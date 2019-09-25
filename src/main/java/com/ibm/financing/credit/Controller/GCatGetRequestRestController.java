package com.ibm.financing.credit.Controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.financing.credit.persistence.bo.CreditRequestInfo;
import com.ibm.financing.credit.persistence.model.stage.CreditRequestEntity;
import com.ibm.financing.credit.persistence.model.stage.CreditRequestInfoBO;
import com.ibm.financing.credit.persistence.model.stage.EntityComment;

@RestController
@Transactional
public class GCatGetRequestRestController
{
   
	private static final Logger logger = LoggerFactory.getLogger(GCatGetRequestRestController.class);
	
	@PersistenceContext
    private EntityManager em;
	
	@Autowired
	private CreditRequestInfoBO creditRequestInfoBO;

	
	
	@RequestMapping(value="/")
	public @ResponseBody List<EntityComment> demo() {
		
		
		TypedQuery<EntityComment> query = em.createNamedQuery("EntityComment.findAll", EntityComment.class);
		

		List<EntityComment> commentList = query.getResultList();
		for (EntityComment next : commentList) {
			System.out.println(next.getEntityId());
			System.out.println(next.getCountryCode());
			logger.info(next.toString());
		}

		return commentList;
		
	}
	
	@RequestMapping(value="/getRequestData")
	public @ResponseBody List<CreditRequestEntity> getCreditRequestList(@RequestParam("creditRequestIId") String creditRequestIId) {
		
	
		TypedQuery<CreditRequestEntity> query = em.createNamedQuery("CreditRequestEntity.findAll", CreditRequestEntity.class);
		query.setParameter(1, creditRequestIId);

		List<CreditRequestEntity> commentList = query.getResultList();
		for (CreditRequestEntity next : commentList) {
			System.out.println(next.getEntityId());
			System.out.println(next.getCounrtyCode());
			logger.info(next.toString());
		}

		return commentList;
		
	}
	
	
	@RequestMapping(value = "/insertRequestData", method = RequestMethod.POST)
	public  ResponseEntity<CreditRequestInfo>  inserDataInCreditRequestTableTemp(@RequestBody CreditRequestInfo creditRequestInfo) {
		
	BeanUtils.copyProperties(creditRequestInfo, creditRequestInfoBO);
	 em.persist(creditRequestInfoBO);
	 return new ResponseEntity<CreditRequestInfo>(creditRequestInfo, HttpStatus.OK);
		
	}
	
	
	
	@RequestMapping(value="/getRequestDataTemp")
	public @ResponseBody List<CreditRequestInfoBO> getCreditRequestListTemp(@RequestParam("creditRequestIId") String creditRequestIId) {
		TypedQuery<CreditRequestInfoBO> query = em.createNamedQuery("CreditRequestInfoBO.findAll", CreditRequestInfoBO.class);
		query.setParameter(1, creditRequestIId);
		List<CreditRequestInfoBO> commentList = query.getResultList();
	
		return commentList;
		
	}
		
	
	

      
    
    
}