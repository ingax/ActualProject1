window.onload = () => {
	getEmployeeById();
}

const getEmployeeById = () => {
	const xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = () => {
		if (xhr.status === 200 && xhr.readyState === 4) {
			const json = xhr.responseText;
			console.log("After json" + json);
			addToTable(JSON.parse(json));
			console.log(json);
		}
	};
	
	xhr.open("GET", "http://localhost:8088/ERS/api/employeeID");
	xhr.send();
}


const addToTable =(username) => {
	const empId = document.createElement("td");
	const empFn = document.createElement("td");
	const empLn = document.createElement("td");
	const empAddress = document.createElement("td");
	const empPn = document.createElement("td");
	const empEmail = document.createElement("td");
	
	empId.textContent = username.employee_Id;
	empFn.textContent = username.first_name;
	empLn.textContent = username.last_name;
	empAddress.textContent = username.address;
	empPn.textContent = username.phone_number;
	empEmail.textContent = username.email;
	
	const row = document.createElement("tr");
	
	row.appendChild(empId);
	row.appendChild(empFn);
	row.appendChild(empLn);
	row.appendChild(empAddress);
	row.appendChild(empPn);
	row.appendChild(empEmail);
	
	document.getElementById("EmployeeTable").appendChild(row);
}