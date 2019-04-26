package ers.serviceimpl;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ers.beans.Forms;
import ers.dao.FormsDao;
import ers.daoimpl.FormsDaoImpl;
import ers.service.FormService;

public class FormServiceImpl implements FormService	{

	private final FormsDao fDao = new FormsDaoImpl();
	private final ObjectMapper mapper = new ObjectMapper();
	@Override
	public List<Forms> getAllForms(HttpServletRequest request, HttpServletResponse response) {
		return fDao.getAllForms();
	}

	@Override
	public Forms getFormById(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("form service by id");
		HttpSession session = request.getSession(false);
		Integer formById = (Integer) session.getAttribute("formId");
		System.out.println("form in FormServImp testing session " + formById);
		if(formById == null)
			return new Forms();
		return fDao.getFormById(formById);
	}
	
	@Override
	public Forms createForm(HttpServletRequest request, HttpServletResponse response) {
			System.out.println("form service by id");
			Forms form = null;
			try {
				form = mapper.readValue(request.getInputStream(), Forms.class);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return fDao.createForm(form);
	}

	@Override
	public Forms updateForm(HttpServletRequest request, HttpServletResponse response) {
		try {
			Forms form = mapper.readValue(request.getInputStream(), Forms.class);
			return fDao.updateForm(form);
		}catch(IOException e) {
			return null;
		}
	}

	@Override
	public long deleteForm(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return 0;
	}

}
