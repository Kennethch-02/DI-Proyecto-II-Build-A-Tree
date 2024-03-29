/**
 * Esta clase corresponde al codigo del arbol AVL, el cual es un arbol binario de busqueda y se caracteriza principalmente por estar balanceado.
 * @autor Kenneth Castillo, Olman Rodriguez y Montserrat Monge.
 * @version 08/12/2020
 */
public class AVL{
    private NodeAVL root;
    public AVL(){
        root = null;
    }
    public NodeAVL obtenerRaiz(){
        return root;
    }
    /**
     * @param d el que se esta buscando
     * @param r funciona para llamar los datos del nodo
     * @return busqyeda
     */
    public NodeAVL buscar(int d, NodeAVL r){
        if (root == null){
            return null;
        }else if ((int)r.data == d){
            return r;
        }else if  ((int) r.data < d){
            return buscar(d,r.right);
        }else{
            return buscar(d,r.left);
        }
    }
    //Obtener el Factor de Equilibrio
    /**
     * @param x se  utiliza para obeter el factor de balanceo
     * @return obtiene el factor
     */
    public int obtenerFE(NodeAVL x){
        if (x == null){
            return -1;
        }else {
            return x.height;
        }
    }
    //rotacion Simple izquierda
    /**
     * @param c nodo que se rotara
     * @return rotacion hacia la izquierda del arbol
     */
    public NodeAVL rotacionIzquierda(NodeAVL c){
        NodeAVL auxiliar = c.left;
        c.left = auxiliar.right;
        auxiliar.right = c;
        c.height = Math.max(obtenerFE(c.left),obtenerFE(c.right))+1;
        auxiliar.height = Math.max(obtenerFE(auxiliar.left),obtenerFE(auxiliar.right))+1;
        return auxiliar;
    }
    //Rotacion simple derecha
    /**
     * @param c nodo que se rotara
     * @return rotacion hacia la derecha del arbol
     */
    public NodeAVL rotacionDerecha(NodeAVL c){
        NodeAVL auxiliar = c.right;
        c.right = auxiliar.left;
        auxiliar.left = c;
        c.height = Math.max(obtenerFE(c.left),obtenerFE(c.right))+1;
        auxiliar.height = Math.max(obtenerFE(auxiliar.left),obtenerFE(auxiliar.right))+1;
        return auxiliar;
    }
    //Rotacion Doble a la Derecha
    /**
     * @param c nodo que se rotara
     * @return rotacion doble hacia la izquierda del arbol
     */
    public NodeAVL rotacionDobleIzquierda(NodeAVL c){
        NodeAVL temporal;
        c.left = rotacionDerecha(c.left);
        temporal = rotacionIzquierda(c);
        return temporal;
    }
    //Rotacion Doble a la Izquierda
    /**
     * @param c nodo que se rotara
     * @return rotacion doble hacia la derecha del arbol
     */
    public NodeAVL rotacionDobleDerecha(NodeAVL c){
        NodeAVL temporal;
        c.right = rotacionIzquierda(c.right);
        temporal = rotacionDerecha(c);
        return temporal;
    }
    //Metodo insertar AVL
    /**
     * @param nuevo nuevo nodo a insertar
     * @param subAr sub arbol del arbol
     * @return la insercion del nuevo nodo
     */
    public NodeAVL insertarAVL(NodeAVL nuevo, NodeAVL subAr){
        NodeAVL nuevoPadre = subAr;
        if ((int)nuevo.data < (int)subAr.data){
            if (subAr.left == null){
                subAr.left = nuevo;
            }else {
                subAr.left = insertarAVL(nuevo, subAr.left);
                if((obtenerFE(subAr.left))-(obtenerFE(subAr.right)) == 2){
                    if ((int)nuevo.data < (int)subAr.right.data){
                        nuevoPadre = rotacionIzquierda(subAr);
                    }else {
                        nuevoPadre = rotacionDobleIzquierda(subAr);
                    }
                }
            }
        }else if ((int)nuevo.data > (int)subAr.data){
            if (subAr.right == null){
                subAr.right = nuevo;
            }else{
                subAr.right = insertarAVL(nuevo,subAr.right);
                if ((obtenerFE(subAr.right))-(obtenerFE(subAr.left)) == 2){
                    if ((int)nuevo.data > (int)subAr.right.data){
                        nuevoPadre = rotacionDerecha(subAr);
                    }else{
                        nuevoPadre = rotacionDobleDerecha(subAr);
                    }
                }
            }
        } else {
            System.out.println("Nodo duplicado");
        }
        //Actualizar la altura
        if ((subAr.left == null) && (subAr.right != null)){
            subAr.height = subAr.right.height+1;
        }else if ((subAr.right==null) && (subAr.left != null)){
            subAr.height = subAr.left.height+1;
        }else {
            subAr.height = Math.max(obtenerFE(subAr.left),obtenerFE(subAr.right))+1;
        }
        return nuevoPadre;
    }
    //Metodo para insertar
    public void insertar(int d){
        NodeAVL nuevo = new NodeAVL(d);
        if (root == null){
            root = nuevo;
        }else {
            root = insertarAVL(nuevo, root);
        }
    }
    //Recorridos
    //Método para recorrer el Arbol InOrden
    /**
     * Recorre el arbol por el recorrido in/orden
     * @param r nodo del arbol
     */
    public void inOrden(NodeAVL r){
        if(r != null){
            inOrden(r.left);
            System.out.println(r.data + ", ");
            inOrden(r.right);
        }
    }
    //Metodo para recorrer el Arbol Preorden
    /**
     * Recorre el arbol por el recorrido pre/orden
     * @param r nodo del arbol
     */
    public void preOrden(NodeAVL r){
        if (r != null){
            System.out.println(r.data + ", ");
            preOrden(r.left);
            preOrden(r.right);
        }
    }
    //Metodo para recorrer el Arbol PostOrden
    /**
     * Recorre el arbol por el recorrido pre/orden
     * @param r nodo del arbol
     */
    public void postOrden(NodeAVL r){
        if(r != null){
            System.out.println(r.data + ", ");
            preOrden(r.left);
            preOrden(r.right);
        }
    }
    //Metodo eliminar
    /**
     * @param nodo el nodo a eliminar
     * @return el arbol con el nodo anterior eliminado
     */
    public boolean soloRaiz(NodeAVL nodo){
        if (nodo.getRight() == null && nodo.getLeft() == null){
            nodo = null;
            return true;
        }
        return false;
    }
    public NodeAVL EliminarNodo(NodeAVL nodo, int dato){
        NodeAVL subAr;
        subAr = root;
        if(soloRaiz(nodo)){
            return null;
        }
        if(nodo == null){
            System.out.println("No se encuentra el nodo");
        }else if (dato < (int)nodo.getData()){
            NodeAVL left;
            left = EliminarNodo(nodo.getLeft(), dato);
            nodo.setLeft(left);
        }else if (dato > (int)nodo.getData()){
            NodeAVL right;
            right = EliminarNodo(nodo.getRight(),dato);
            nodo.setData(right);
        }else{
            NodeAVL eliminar;
            eliminar = nodo;
            if (eliminar.getLeft() == null){
                nodo = eliminar.getRight();
            }else if (eliminar.getRight() == null){
                nodo = eliminar.getLeft();
            }else {
                eliminar = reemplazar(eliminar);
            }
            eliminar = null;
            if ((subAr.left == null) && (subAr.right != null)){
                subAr.height = subAr.right.height+1;
            }else if ((subAr.right==null) && (subAr.left != null)){
                subAr.height = subAr.left.height+1;
            }else {
                subAr.height = Math.max(obtenerFE(subAr.left),obtenerFE(subAr.right))+1;
            }
        }
        return nodo;
    }
    //Metodo para eliminar
    public void eliminar(int d){
        NodeAVL nuevo = new NodeAVL(d);
        if (root == null){
            root = nuevo;
        }else {
            root = EliminarNodo(root, d);
        }
    }
    /**
     * @param nodo nodo que sera reemplazado
     * @return el arbol con el nodo anterior reemplazado
     */
    public NodeAVL reemplazar(NodeAVL nodo){
        NodeAVL N1;
        NodeAVL N2;
        N2 = nodo;
        N1 = nodo.getLeft();

        while (N1.getRight() != null){
            N2 = N1;
            N1 = N1.getRight();
        }
        nodo.setData(N1.getData());
        if (N2 == nodo){
            N2.setLeft(N1.getLeft());
        }
        else {
            N2.setRight(N1.getLeft());
        }
        return N1;
    }

}
