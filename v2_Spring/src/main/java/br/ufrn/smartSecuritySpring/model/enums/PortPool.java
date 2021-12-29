package br.ufrn.smartSecuritySpring.model.enums;

public enum PortPool {
	LOAD_BALANCER(8081), SERVER_A(8082), SERVER_B(8083), DATABASE(8084), BACKUP_DATABASE(8085);
	
	public Integer port;
	PortPool(Integer port) {
		this.port = port;
	}
}
