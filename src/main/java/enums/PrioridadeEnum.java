package enums;

public enum PrioridadeEnum {
	
	BAIXA(0, "BAIXA"),
	MEDIA(1, "MEDIA"),
	ALTA(2, "ALTA");
	
	private Integer codigo;
	private String descricao;
	
	private PrioridadeEnum(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
		
	public static PrioridadeEnum toEnums(Integer cod) {
		
		if(cod == null)
			return null;
		for(PrioridadeEnum x : PrioridadeEnum.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Prioriadade inválido: " + cod);
	}
	

}
