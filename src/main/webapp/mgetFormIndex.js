window.onload = () => {
	getAllForms();
}

const getAllForms = () => {
	const xhr = new XMLHttpRequest();
	xhr.onreadystatechange = () => {
		if (xhr.status === 200 && xhr.readyState === 4) {
			const json = xhr.responseText;
			console.log(json);
			makeTable(JSON.parse(json));
		}
	};
	
	xhr.open("GET", "http://localhost:8088/ERS/api/forms");
	xhr.send();
}

const makeTable = (listOfRequest) => {
	for(let request of listOfRequest){
		const formId = document.createElement("td");
		const amount = document.createElement("td");
		const reason = document.createElement("td");
		const employeeId = document.createElement("td");
		
		formId.textContent = request.form_id;
		amount.textContent = request.amount;
		reason.textContent = request.reason;
		employeeId.textContent = request.employee_id;
		
		const row = document.createElement("tr");
		
		row.appendChild(formId);
		row.appendChild(amount);
		row.appendChild(reason);
		row.appendChild(employeeId);
		
		document.getElementById("RequestTable").appendChild(row);
	}
}