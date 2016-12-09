var rutina= function (nombreRutina,contador,nom,aux,vari,id)
{
    this.nombreRutina=nombreRutina
    this.id=id;
    this.contadorLinea=contador;
    this.nombre=nom;
    this.aux=aux;
    this.variables=vari;
    
}
var node = function()
{
    var data;
    var next = null;
}
  
var stack = function()
{
    this.top = null;
  
    this.push = function(data) {
        if (this.top == null) {
            this.top = new node();
            this.top.data = data;
        } else {
            var temp = new node();
            temp.data = data;
            temp.next = this.top;
            this.top = temp;
        }
    }
  
    this.pop = function() {
        var temp = this.top;
        var data = this.top.data;
        this.top = this.top.next;
        temp = null;
        return data;
    }
    this.actualizar = function(contador) {
      
      this.top.data.contadorLinea=contador
    }
    this.actualizar1 = function(aux) {
      
      this.top.data.aux=aux
    }
    this.peek= function ()
    {
        var data=this.top.data;
        return data;
    }
  
    this.print = function() {
        var node = this.top;
        while (node != null) {
            console.log(node.data);
            node = node.next;
        }
    }
}
  

