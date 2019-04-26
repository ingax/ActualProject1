package ers.beans;


public class Forms {
	private int form_id;
	private double amount;
	private String reason;
	private String decisionMade;
	private String employee_id;
	private String manager_id;
	
	public Forms() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Forms(int form_id, double d, String reason, String decisionMade,
			String employee_id, String manager_id) {
		super();
		this.form_id = form_id;
		this.amount = d;
		this.reason = reason;
		this.decisionMade = decisionMade;
		this.employee_id = employee_id;
		this.manager_id = manager_id;
	}

	public int getForm_id() {
		return form_id;
	}

	public void setForm_id(int form_id) {
		this.form_id = form_id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDecisionMade() {
		return decisionMade;
	}

	public void setDecisionMade(String decisionMade) {
		this.decisionMade = decisionMade;
	}

	public String getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}

	public String getManager_id() {
		return manager_id;
	}

	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((decisionMade == null) ? 0 : decisionMade.hashCode());
		result = prime * result + ((employee_id == null) ? 0 : employee_id.hashCode());
		result = prime * result + form_id;
		result = prime * result + ((manager_id == null) ? 0 : manager_id.hashCode());
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Forms other = (Forms) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (decisionMade == null) {
			if (other.decisionMade != null)
				return false;
		} else if (!decisionMade.equals(other.decisionMade))
			return false;
		if (employee_id == null) {
			if (other.employee_id != null)
				return false;
		} else if (!employee_id.equals(other.employee_id))
			return false;
		if (form_id != other.form_id)
			return false;
		if (manager_id == null) {
			if (other.manager_id != null)
				return false;
		} else if (!manager_id.equals(other.manager_id))
			return false;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Forms [form_id=" + form_id + ", amount=" + amount + ", reason=" + reason + ", decisionMade="
				+ decisionMade + ", employee_id=" + employee_id + ", manager_id=" + manager_id + "]";
	}

	
	
	
	
}
