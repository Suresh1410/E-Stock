import React, { Component } from "react";
import StockService from '../../services/stock/stock.service.js';
import CompanyService from '../../services/company/company.service.js';
import DropdownButton from 'react-bootstrap/DropdownButton';
import Dropdown from 'react-bootstrap/Dropdown'

export default class AddStock extends Component {
    constructor(props) {
        super(props);

        this.state = {
            companyCode: "",
            companyName: "",
            stockPrice: "",
            companies: [],
            submitted: false,
            allfieldsRequired: false
        };
    }

    componentDidMount() {
        CompanyService.getAllCompany()
            .then(response => {
                if (response && response.data) {
                    this.setState({
                        companies: response.data
                    });
                }
            })
            .catch(e => {
                console.log(e);
            });
    }

    handleChange = (event) => {
        const { name, value } = event.target;

        switch (name) {
            case "stockPrice":
                var regExp = new RegExp(/^\d*\.?\d*$/);

                if (value === '' || regExp.test(value)) {
                    this.setState({
                        stockPrice: value
                    });
                }
                break;
            default:
                break;
        }
    }

    handleSelect = (e) => {
        let cCode = e.split(",,,,,,")[0];
        let cName = e.split(",,,,,,")[1];
        this.setState({
            companyCode: cCode,
            companyName: cName
        });
    }

    addStock = (e) => {
        e.preventDefault();
        if (this.state.companyCode && this.state.stockPrice) {

            var data = {
                companyCode: this.state.companyCode,
                price: parseFloat(this.state.stockPrice),
                stockPriceDttm: new Date()
            };

            StockService.addStock(data,this.state.companyCode)
                .then(response => {
                    this.setState({
                        companyCode: "",
                        companyName: "",
                        stockPrice: "",
                        submitted: true,
                        allfieldsRequired: false
                    });
                    console.log(response.data);
                })
                .catch(e => {
                    this.setState({
                        submitted: true,
                        allfieldsRequired: false,
                        error: true
                    });
                    console.log(e);
                });
        }
        else {
            this.setState({
                allfieldsRequired: true
            });
        }
    }

    render() {
        return (
            <form className="companyForm" onSubmit={this.addStock}>
                <h2>Add Stock Price</h2>
                {this.state.submitted ? (
                    <label style={{ color: "green" }}>Stock added successfully!</label>
                ) :
                    this.state.allfieldsRequired ? (
                        <label style={{ color: "red" }}>All are Mandatory fields!</label>
                    ) : this.state.error ? (
                        <label style={{ color: "red" }}>Error Occured!</label>
                    ) : (
                        <label ></label>
                    )}
                <div>
                    <div className="form-group">
                        <DropdownButton alignRight title="Select Company" onSelect={this.handleSelect}>
                            {this.state.companies.map(item => (
                                <Dropdown.Item key={item.companyCode} eventKey={item.companyCode + ",,,,,," + item.companyName}>{item.companyName}</Dropdown.Item>
                            ))
                            }
                        </DropdownButton>
                    </div>
                    <div className="form-group">
                        <label htmlFor="compName">Company Name</label>
                        <input
                            id="compName"
                            type="text"
                            className="form-control"
                            value={this.state.companyName}
                            readOnly
                            required
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="stockPrice">Stock Price</label>
                        <input
                            type="text"
                            className="form-control"
                            id="stockPrice"
                            required
                            value={this.state.stockPrice}
                            onChange={this.handleChange}
                            name="stockPrice"
                        />
                    </div>
                    <button className="btn btn-success right">
                        Add
                    </button>
                </div>
            </form>
        );
    }
};