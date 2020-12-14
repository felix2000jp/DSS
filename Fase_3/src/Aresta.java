import java.util.TreeMap;

public class Aresta {
    int inicio;
    int destino;
    double peso;

    Aresta()
    {
        this.inicio = -1;
        this.destino = -1;
        this.peso = -1;
    }

    Aresta(Aresta a)
    {
        this.inicio = a.getInicio();
        this.destino = a.getDestino();
        this.peso = a.getPeso();
    }

    Aresta(int inicio, int destino, double peso)
    {
        this.inicio = inicio;
        this.destino = destino;
        this.peso = peso;
    }

    public int getInicio()
    {
        return inicio;
    }

    public int getDestino()
    {
        return destino;
    }

    public double getPeso()
    {
        return peso;
    }

    public void setInicio(int inicio)
    {
        this.inicio = inicio;
    }

    public void setDestino(int destino)
    {
        this.destino = destino;
    }

    public void setPeso(double peso)
    {
        this.peso = peso;
    }

    @Override
    public Object clone()
    {
        return new Aresta(this);
    }


}
