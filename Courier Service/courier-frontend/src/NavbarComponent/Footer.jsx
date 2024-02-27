import { Link } from "react-router-dom";
const Footer = () => {
  return (
    <div>
      <div class="container my-5">
        <footer class="text-center text-lg-start text-color">
          <div class="container-fluid p-4 pb-0">
            <section class="">
              <div class="row">
                <div class="col-lg-12 col-md-6 mb-4 mb-md-0">
                  <h5 class="text-uppercase text-color-second">
                  STS Courier Services
                  </h5>

                  <p>
                    Empower your logistics with our cutting-edge STS Courier
                    Services . From swift deliveries to personalized
                    service, we redefine efficiency. Trust us for seamless
                    shipping - where every package finds its perfect journey.
                  </p>
                </div>

              
               
              </div>
            </section>

            <hr class="mb-4" />

            <section class="">
              <p class="d-flex justify-content-center align-items-center">
                <span class="me-3 text-color">Login from here</span>
                <Link to="/user/login" class="active">
                  <button
                    type="button"
                    class="btn btn-outline-light btn-rounded bg-color custom-bg-text"
                  >
                    Log in
                  </button>
                </Link>
              </p>
            </section>

            <hr class="mb-4" />
          </div>

          <div class="text-center">
            © 2024 Copyright: 
           
              STS Courier Services
            
          </div>
        </footer>
      </div>
    </div>
  );
};

export default Footer;
