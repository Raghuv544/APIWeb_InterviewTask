package api.Resources;
//enum is a special class in java which has collection of constants or  methods
public enum APIResources {
	
	getHealthzAPI("/healthz"),
	getTenantAPI("/api/v3/rest/default/eula");
	
	private String resource;
	
	APIResources(String resource)
	{
		this.resource=resource;
	}
	
	public String getResource()
	{
		return resource;
	}
	

}
