import React from "react";
import { Switch, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "@fortawesome/fontawesome-free/css/all.css";
import "@fortawesome/fontawesome-free/js/all.js";
import "./App.css";

import AddOrder from "./components/AddOrder";
import Order from "./components/Order";
import OrdersList from "./components/OrdersList";

function App() {
  return (
    <div>
      <nav className="navbar navbar-expand navbar-dark bg-dark">
        <div className="navbar-nav mr-auto">
          <li className="nav-item">
            <Link to={"/order"} className="nav-link">
              Pedidos
            </Link>
          </li>
          <li className="nav-item">
            <Link to={"/addorder"} className="nav-link">
              Novo pedido
            </Link>
          </li>
        </div>
      </nav>

      <div className="container mt-3">
        <Switch>
          <Route exact path={["/", "/order"]} component={OrdersList} />
          <Route exact path="/addorder" component={AddOrder} />
          <Route path="/order/:id" component={Order} />
        </Switch>
      </div>
    </div>
  );
}

export default App;
