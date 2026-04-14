package enums;

public enum PerfilEnum {

	ADMIN(0, "ROLE_ADMIN"), 
	CLIENTE(1, "ROLE_CLIENTE"),
	TECNICO(2, "ROLE_TECNICO"),
	ERRO(3,"ROLE_TECNICO");
	
	private Integer codigo;
	private String descricao;
	
	private PerfilEnum(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static PerfilEnum toEnums(Integer cod) {
		try {
			
			if(cod == null)
				return null;
			for(PerfilEnum x : PerfilEnum.values()) {
				if(cod.equals(x.getCodigo())) {
					return x;
				}
			}
			
			
		} catch (Exception e) {
			throw new IllegalArgumentException("Perfil inválido: " + cod);
		}
		
		return null;
	}
	
}
