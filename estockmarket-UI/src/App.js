import React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import Menu from "./components/menu/Menu";
import Home from "./components/home/Home";
import RegisterCompany from "./components/company/RegisterCompany";
import CompanyList from "./components/company/CompanyList";
import AddStock from "./components/stock/AddStock";
import StockList from "./components/stock/StockList";
import "./App.css";

function App() {
  return (
    <div className="container">
      <Router>
        <Menu />
        {<Switch>
          <Route path="/" exact component={Home} />
          <Route path="/company" exact component={CompanyList} />
          <Route path="/company/register" exact component={RegisterCompany} />
          <Route path="/stock" exact component={StockList} />
          <Route path="/stock/add" exact component={AddStock} />
        </Switch>}
      </Router>
    </div>
  );
}

export default App;
