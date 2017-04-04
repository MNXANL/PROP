//una seleccion de entre k opciones sin ningun orden implícito
public class RespCualitativaNoOrdenadaUnica extends Respuesta{

    private int seleccion;

    public RespCualitativaNoOrdenadaUnica(int n){
        seleccion = n;
    }
    public int get(){
        return seleccion;
    }
    public void set(int n){
        seleccion = n;
    }
}
