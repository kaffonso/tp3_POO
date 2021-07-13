package PaDi;

import PaDi.backend.app.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EpassesData {
    private static List<EpassStandard> ePasses;

    public void carregar() {
        ePasses = new ArrayList<EpassStandard>();
        EpasseJovem jovem = new EpasseJovem(new DocumentoID("19273",
                TipoDocumento.BI),"Kenny Afonso", LocalDate.of(1999,11,11), "Craquinha", "kennyaafonso@gmail.com", 5977491);
        jovem.setSaldo(100);

        EpasseTI teridade = new EpasseTI(new DocumentoID("24234",
                TipoDocumento.BI),"Pedro Andrade", LocalDate.of(1950,1,1), "Mindelo", "pedroadnrade@gmail.com", 9993492);


        EpasseFuncionario funcio = new EpasseFuncionario(new DocumentoID("12212",
                TipoDocumento.BI),"Fernando Pires", LocalDate.of(1978,5,15), "Bela Vista", "fern@gmail.com", 9982634);


        EpasseStudent student = new EpasseStudent("6273",TipoDocumento.BI,"Kelly Fortes",LocalDate.of(2003,7,27),"Vila Nova","kfortes@gmail.com", 9737373, "Ludjero Lima", 11);

        ePasses.add(jovem);
        ePasses.add(teridade);
        ePasses.add(funcio);
        ePasses.add(student);
    }

    public List<EpassStandard> getTodoItems() {
        return ePasses;
    }


    public void addEpass(EpassStandard item) {
        ePasses.add(item);
        System.out.println("Adicionado");
    }

    public void listar(){
        for (EpassStandard ePass : ePasses) {
            System.out.println(ePass.getTitular().getNome());
        }
    }

    public int tamanho(){
        int cont = 0;
        for (int i=0; i<ePasses.size();i++){
            cont++;
        }
        return cont;
    }
}
