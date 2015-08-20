package br.com.fpu.lavajato;

public class CalculaNota {
	private String nome;
	private String veiculo;
	private String[] servicos;
	private Double lavar = 0.00;
	private Double aspirar = 0.00;
	private Double encerar = 0.00;
	private Double subtotal = 0.00;
	private Double desconto = 0.00;
	private Double totalNota = 0.00;

	public void calculoNota() {

		switch (veiculo) {
		case "carro":
			lavar = 20.0;
			aspirar = 5.0;
			encerar = 15.0;
			break;
		case "moto":
			lavar = 15.0;
			aspirar = 0.0;
			encerar = 10.0;
			break;
		case "caminhonete":
			lavar = 25.0;
			aspirar = 7.0;
			encerar = 20.0;
			break;

		}

	try {
		
		for (String servico : servicos) {

			switch (servico) {
			case "lavar":
				subtotal += lavar;
				break;
			case "aspirar":
				subtotal += aspirar;
				break;
			case "encerar":
				subtotal += encerar;
				break;
			}
		}

		if (this.servicos.length == 2) {
			desconto = subtotal * 10 / 100;
			totalNota = subtotal - desconto;
		}
		else if (this.servicos.length == 3) {

			desconto = subtotal * 15 / 100;
			totalNota = subtotal - desconto;

		} else {
			this.totalNota = subtotal;
		}
		
	} catch (NullPointerException e) {
		// TODO: handle exception
	}


	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(String veiculo) {
		this.veiculo = veiculo;
	}

	public String[] getServicos() {
		return servicos;
	}

	public void setServicos(String[] servicos) {
		this.servicos = servicos;
	}

	public Double getLavar() {
		return lavar;
	}

	public Double getAspirar() {
		return aspirar;
	}

	public Double getEncerar() {
		return encerar;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public Double getDesconto() {
		return desconto;
	}

	public Double getTotalNota() {
		return totalNota;
	}
	
}
