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
                                        Browse… <input type="file" name="imageFile" id="imgInp" placeholder="Subir imagen">
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
                            <button type="submit" class="btn btn-primary btn-block" text="Editar Juego" onclick="sendForm(event);">${this.gameId != 0 ? html`Editar Juego` : html`Crear Juego`}</button>
                        </div> <!-- form-group// -->                                                                    
                    </form>
                </article>`;
  }

  createRenderRoot() {
    // this is what overrides lit-element's behavior so that the contents don't render in shadow dom
    return this;
  }

} // Register the element with the browser

customElements.define('game-creation-form', GameCreationForm);