import { LitElement, html } from "./node_modules/lit-element/lit-element.js";
export class GameCreationForm extends LitElement {
  /**
   * Define properties. Properties defined here will be automatically
   * observed.
   */
  static get properties() {
    return {
      gameId: {
        type: Number
      },
      gameName: {
        type: String
      },
      gameUrl: {
        type: String
      },
      gameDescription: {
        type: String
      }
    };
  }
  /**
   * In the element constructor, assign default property values.
   */


  constructor() {
    // Must call superconstructor first.
    super();
    this.gameId = 0;
    this.gameName = "";
    this.gameUrl = "";
    this.gameDescription = "";
  }
  /**
   * Implement `render` to define a template for your element.
   */


  render() {
    /**
     * Use JavaScript expressions to include property values in
     * the element template.
     */
    return html`<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
                        <link href="/css/gamecreation.css" rel="stylesheet">
                        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
                        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
                        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
                        <article class="card-body mx-auto" style="max-width: 400px;">
                        <h4 class="card-title mt-3 text-center"  text="Editar Juego">${this.gameId != 0 ? html`Editar Juego` : html`Crear Juego`}</h4>
                        <p class="divider-text">
                            <span></span>
                        </p>
                        <div id="mensajeEdicion" class="alert alert-success" style="display:none;" role="alert"> Juego editado con éxito </div>
                        <div id="mensajeError" class="alert alert-danger" style="display:none;" role="alert"> Error, ya existe el título introducido. </div>
                        ${this.gameId != 0 ? html`<form id="myFormEdit" action="/gameList" method="post" enctype="multipart/form-data">` : html`<form id="myFormAdd" action="/gameList" method="post" enctype="multipart/form-data">`}
                        <input type="hidden" id="gameId" value="${this.gameId}">
                        <div class="form-group input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                            </div>
                            <input id="nameInput" name="name" class="form-control input-text" placeholder="Titulo" type="text" value='${this.gameName}'>
                        </div> <!-- form-group// -->
                        
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-btn">
                                    <span class="btn btn-default btn-file">
                                        Browse… <input type="file" name="imageFile" id="imgInp" @change="${this.changeImage}" placeholder="Subir imagen">
                                    </span>
                                </span>
                                <input id="urlInput" name="url" type="text" class="form-control" value='${this.gameUrl}' readonly>
                            </div>
                            <img name="fileImage" id='img-upload'/>
                        </div>
                        
                        <div class="form-group input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"> <i class="fa fa-envelope"></i> </span>
                            </div>
                            <textarea id="descriptionInput" name="description" class="form-control" placeholder="Descripcion" text='${this.gameDescription}'>${this.gameDescription}</textarea>
                        </div> <!-- form-group// -->
                                                        
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary btn-block" text="Editar Juego" @click="${this.sendForm}">${this.gameId != 0 ? html`Editar Juego` : html`Crear Juego`}</button>
                        </div> <!-- form-group// -->                                                                    
                    </form>
                </article>`;
  }

  changeImage(event) {
    var input = event.target,
        label = input.value.replace(/\\/g, '/').replace(/.*\//, '');
    this.fileSelect(event, [label]);
    this.loadImage();
  }

  fileSelect(event, label) {
    var input = $(event.target).parents('.input-group').find(':text'),
        log = label;

    if (input.length) {
      input.val(log);
    } else {
      if (log) alert(log);
    }
  }

  readURL(input) {
    if (input.files && input.files[0]) {
      var reader = new FileReader();
      var imgUpload = this.shadowRoot.getElementById("img-upload");

      reader.onload = function (e) {
        imgUpload.src = e.target.result;
      };

      reader.readAsDataURL(input.files[0]);
      var frm = new FormData();
      frm.append('imgInp', input.files[0]);
      $.ajax({
        method: 'POST',
        url: 'http://localhost:8078/imageUpload',
        data: frm,
        contentType: false,
        processData: false,
        cache: false
      });
    }
  }

  loadImage() {
    this.readURL(this.shadowRoot.getElementById("imgInp"));
  }

  sendForm(event) {
    event.preventDefault();
    if (!this.checkValues()) return;
    var gameId = this.shadowRoot.getElementById("gameId").value;
    var formSerialized = "name=" + this.shadowRoot.getElementById("nameInput").value + "&" + "url=" + this.shadowRoot.getElementById("urlInput").value + "&" + "description=" + this.shadowRoot.getElementById("descriptionInput").value;
    var mensajeError = this.shadowRoot.getElementById("mensajeError");
    var mensajeEdicion = this.shadowRoot.getElementById("mensajeEdicion");
    var name = this.shadowRoot.getElementById("nameInput").value;

    if (gameId == 0) {
      $.post('http://localhost:8081/games', formSerialized, function (result, status, request) {
        if (request.status == 201) {
          var message = "Juego <strong>" + name + "</strong> creado con éxito.";
          mensajeError.style.display = "none";
          mensajeEdicion.innerHTML = message;
          mensajeEdicion.style.display = "block";
        } else {
          var message = "Error al crear el Juego, ya existe un juego con el Título <strong>" + name + "</strong>";
          mensajeEdicion.style.display = "none";
          mensajeError.innerHTML = message;
          mensajeError.style.display = "block";
        }
      });
    } else {
      $.ajax({
        url: 'http://localhost:8081/games/' + this.shadowRoot.getElementById("gameId").value,
        data: formSerialized,
        method: 'PUT',
        success: function (result) {
          var message = "Juego <strong>" + name + "</strong> editado con éxito.";
          mensajeError.style.display = "none";
          mensajeEdicion.innerHTML = message;
          mensajeEdicion.style.display = "block";
        },
        error: function (result) {
          var message = "Error al editar el Juego, no se ha encontrado el Juego en la Base de Datos o el Título introducido ya existe.";
          mensajeEdicion.style.display = "none";
          mensajeError.innerHTML = message;
          mensajeError.style.display = "block";
        }
      });
    }
  }

  checkValues() {
    if (this.shadowRoot.getElementById("nameInput").value == "") {
      this.showIncorrectMessage("Título es un campo obligatorio");
      return false;
    }

    if (this.shadowRoot.getElementById("urlInput").value == "") {
      this.showIncorrectMessage("El juego debe de tener una imagen.");
      return false;
    }

    if (this.shadowRoot.getElementById("descriptionInput").value == "") {
      this.showIncorrectMessage("Descripción es un campo obligatorio");
      return false;
    }

    return true;
  }

  showCorrectMessage(message) {
    this.shadowRoot.getElementById("mensajeError").style.display = "none";
    this.shadowRoot.getElementById("mensajeEdicion").innerHTML = message;
    this.shadowRoot.getElementById("mensajeEdicion").style.display = "block";
  }

  showIncorrectMessage(message) {
    this.shadowRoot.getElementById("mensajeEdicion").style.display = "none";
    this.shadowRoot.getElementById("mensajeError").innerHTML = message;
    this.shadowRoot.getElementById("mensajeError").style.display = "block";
  }

} // Register the element with the browser

customElements.define('game-creation-form', GameCreationForm);