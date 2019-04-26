window.onload = () => {
	getAllEmployees();
}

const getAllEmployees = () => {
	const xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = () => {
		if (xhr.status === 200 && xhr.readyState === 4) {
			const json = xhr.responseText;
			makeTable(JSON.parse(json));
			console.log(json);
		}
	};
	
	xhr.open("GET", "http://localhost:8088/ERS/api/employees");
	xhr.send();
}

const makeTable = (listOfEmployees) => {
	for(let employee of listOfEmployees){
		const empId = document.createElement("td");
		const empFn = document.createElement("td");
		const empLn = document.createElement("td");
		const empAddress = document.createElement("td");
		const empPn = document.createElement("td");
		const empEmail = document.createElement("td");
		
		empId.textContent = employee.employee_Id;
		empFn.textContent = employee.first_name;
		empLn.textContent = employee.last_name;
		empAddress.textContent = employee.address;
		empPn.textContent = employee.phone_number;
		empEmail.textContent = employee.email;
		
		const row = document.createElement("tr");
		
		row.appendChild(empId);
		row.appendChild(empFn);
		row.appendChild(empLn);
		row.appendChild(empAddress);
		row.appendChild(empPn);
		row.appendChild(empEmail);
		
		document.getElementById("EmployeeTable").appendChild(row);
	}
}