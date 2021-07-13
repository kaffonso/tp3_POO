package PaDi.backend.app;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Interface java que representa todas as operações 
 * a classe PaDi deverá implementar
 * @author Paulo Silva
 * @version 21-07-2020
 *
 */

public interface FuncioPaDi {
	//public ArrayList<EPass> importDados_ePass(ObjectInputStream oin, String nomeF) throws IOException,ClassNotFoundException;
	public void exportDados_ePass(ObjectOutputStream oout, String nome) throws IOException;
	public String info_ePass();
	public void pagarViagem(EPass passe);
	public int passarSaldo(EPass origem, EPass destino);
	public <T extends EPass> void mostrarDados_ePass(T passe);
	public void carregar(EPass passe, double valor);
	public void alterarDados(EPass passe); 
	public void novo(String tipoE);
	public EPass findEPass(int codigo);
	public int find(int codigo);
	public EPass lerDadosEpass(LeituraPorTipo tipo);
	public int leCodigoEpass();
	public ArrayList<EPass> importDados_ePass(ObjectInputStream oin, String nomeF)
			throws IOException, ClassNotFoundException, CloneNotSupportedException;

}
