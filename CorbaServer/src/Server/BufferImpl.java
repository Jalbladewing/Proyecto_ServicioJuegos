package Server;

import org.omg.CORBA.ORB;

import BufferApp._BufferImplBase;

/**
* Agregando funcionalidad al componente Buffer
*/
class BufferImpl extends _BufferImplBase {
    private ORB orb;
    private String buf[];
    private int elementos;
    private static int maxElementos = 10;
    // implementa el metodo constructor
    BufferImpl (){
       buf = new String[maxElementos];
       elementos = 0;
    }
    // implementa el metodo put()
    public boolean put (String elemento){
       if(elementos<maxElementos) {
             buf[elementos]=elemento;
             elementos++;
             System.out.println(buf[elementos-1]+"\tElementos: "+elementos);
             return true;
        } else {
                    elemento="PILA LLENA";
                    System.out.println("PILA LLENA");
                    return false;
            }
    }
    // implementa el metodo get()
    public boolean get (org.omg.CORBA.StringHolder elemento){
        int i;
        if(elementos!=0) {
               elemento.value=buf[0];
               for(i=0;i<maxElementos-1;i++)
                    buf[i]=buf[i+1];
               elementos--;
               System.out.println("MÉTODO GET: " + elemento.value +"\tElementos: "+elementos);
              return true;
         } else { elemento.value="NO SE PUEDE LEER SIN NOTICIAS"; return false;}
    }
    // implementa el metodo read()
    public boolean read (org.omg.CORBA.StringHolder elemento){
          if(elementos!=0) {
                 elemento.value=buf[0];
                 System.out.println("MÉTODO READ: " + elemento.value +"\tElementos: "+elementos);
                 return true;
          } else { elemento.value="NO SE PUEDE PREVISUALIZAR SIN NOTICIAS"; return false;}
    }
    
    public boolean canPut()
    {
    	if(elementos < maxElementos) return true;
    	return false;
    }
    
	public int getSize() {
		
		return elementos;
	}
	
    // implementa el metodo shutdown()
    public void shutdown() {
          orb.shutdown(false);
    }
}