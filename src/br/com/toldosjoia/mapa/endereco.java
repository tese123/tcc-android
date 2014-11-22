package br.com.toldosjoia.mapa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.toldosjoia.dados.Cliente;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;


public class endereco {

	private final Address address;
	Cliente cliente;
	Geocoder gc;
	int id;
	private String nome;
	public endereco(Address address, int id) {
		this.address = address;
		this.id = id;
	}

	public static List<endereco> buscar(Context context, ArrayList<Cliente> cliente ) {
		Geocoder gc = new Geocoder(context, new Locale("pt","BR"));
		List<Address> addresses = null;
		try {
			List<endereco> enderecos = new ArrayList<endereco>();
			for (int x = 0; x < cliente.size(); x++){
				Log.i("me", getDesc(cliente.get(x)));
			addresses = gc.getFromLocationName(getDesc(cliente.get(x)), 1);
			if(addresses != null && addresses.size() > 0){
				     Log.i("GetDesc", "enderecos add " + addresses.get(0).getLatitude());
					enderecos.add(new endereco(addresses.get(0), x));
					enderecos.get(enderecos.size()-1).setNome(cliente.get(x).getNome());
			                   }
			}
				return enderecos;
			
		} catch (IOException e) {
			
			System.out.println("Deu erro o geo coder - " + e.getMessage());
		}
		return null;
	}


	public static String getDesc(Cliente cliente) {
		return cliente.getNumero() + cliente.getrua() + ", "+cliente.getBairro() +", "+  cliente.getCidade() +"- "+cliente.getEstado();
	}

	public double getLatitude() {
		return address.getLatitude();
	}

	public double getLongitude() {
		return address.getLongitude();
	}
	public String getNome()
	{
		return nome;
	}
	public void setNome(String nome){
		this.nome = nome;
	}
 }

