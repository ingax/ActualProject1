window.onload = () => {
	document.getElementById("createForm").addEventListener("click", createForm);
	//getFormById();
}

const createForm = () => {
	console.log("in create form formsindex.js")
	const xhr = new XMLHttpRequest();
	
	const data = parseForm();
	
	xhr.onreadystatechange = () => {
		if(xhr.status === 200 && xhr.readyState === 4){
			const json = xhr.responseText;
			console.log("After json cF" + json);
			console.log(json);
			makeFormTable(JSON.parse(json));
		}
	}
		xhr.open("POST", "http://localhost:8088/ERS/api/forms");
		
		xhr.send(JSON.stringify(data));
}

const parseForm = () => {
	const formId = document.getElementById("form_id").value;
	const amountVal = document.getElementById("amount").value;
	const reasonVal = document.getElementById("reason").value;
	const empId = document.getElementById("employee_id").value;
	 return {
		 form_id: formId,
		 amount: amountVal,
		 reason: reasonVal,
		 decisionMade: "",
		 employee_id: empId,
		 manager_id: ""
	 };
}

const getFormById = (json) => {
	/*const xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = () => {
		if (xhr.status === 200 && xhr.readyState === 4) {
			const json = xhr.responseText;
			console.log("After json gF "+ json);
			makeFormTable(JSON.parse(json));
			console.log(json);
		}
	};
	
	xhr.open("GET", "http://localhost:8088/ERS/api/formID");
	xhr.send();*/
}

const makeFormTable = (form) => {
		const formId = document.createElement("td");
		const formAmount = document.createElement("td");
		const formReason = document.createElement("td");
		const formEmpId = document.createElement("td");

		
		formId.textContent = form.form_id;
		formAmount.textContent = form.amount;
		formReason.textContent = form.reason;
		formEmpId.textContent = form.employee_id;

		
		const row = document.createElement("tr");
		
		row.appendChild(formId);
		row.appendChild(formAmount);
		row.appendChild(formReason);
		row.appendChild(formEmpId);
		
		document.getElementById("FormTable").appendChild(row);
}