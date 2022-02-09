package br.ufrn.smartSecurityEureka;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

@Component
public class BetaRestTemplateClient {
	@Autowired
	private DiscoveryClient discoveryClient;
	
	public void listBetaServices() {
		List<ServiceInstance> instances = discoveryClient.getInstances("SMART-SECURITY-BETA-SERVER");
		if(instances.size() == 0)
			return;
		for(Iterator iterator = instances.iterator(); iterator.hasNext();) {
			ServiceInstance serviceInstance = (ServiceInstance) iterator.next();
			System.out.println(serviceInstance.getHost() + ":" + serviceInstance.getPort());
		}
	}
}
