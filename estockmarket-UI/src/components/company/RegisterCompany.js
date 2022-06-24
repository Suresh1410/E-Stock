import React, { Component } from "react";
import CompanyService from '../../services/company/company.service.js';

export default class RegisterCompany extends Component {
    constructor(props) {
        super(props);

        this.state = {
            code: "",
            name: "",
            ceoName: "",
            turnOver: "",
            website: "",
            stockExchange: "",
            submitted: false,
            errors: "",
            error: false,
            compCodeError: false,
            websiteError: false
        };
    }

    handleChange = (event) => {
        const { name, value } = event.target;

        switch (name) {
            case "companyCode":
                var regExpCode = new RegExp(/[^0-9a-zA-Z]/);
                if (value === '' || !regExpCode.test(value)) {
                    this.setState({
                        code: value
                    });
                }
                break;
            case "companyName":
                this.setState({
                    name: value
                });
                break;
            case "ceoName":
                this.setState({
                    ceoName: value
                });
                break;
            case "turnOver":
                var regExp = new RegExp(/^\d*\.?\d*$/);

                if (value === '' || regExp.test(value)) {
                    this.setState({
                        turnOver: value
                    });
                }
                break;
            case "website":
                this.setState({
                    website: value
                });
                break;
            case "stockExchange":
                this.setState({
                    stockExchange: value
                });
                break;
            default:
                break;
        }
    }

    handleValidation = () =>{
        let errors = {};
        let formIsValid = true;

        if (typeof this.state.code !== "undefined") {
            if (this.state.code.match(/[^0-9a-zA-Z]/)) {
                formIsValid = false;
                errors["code"] = "Only Alphanumeric";
            }
        }

        if (typeof this.state.turnOver !== "undefined") {
            if (parseFloat(this.state.turnOver) < 100000000) {
                formIsValid = false;
                errors["turnOver"] = "Should be more than 10 Cr";
            }
        }

        this.setState({ errors: errors });
        return formIsValid;
    }

    addCompany = (event) => {
        event.preventDefault();
        if (this.handleValidation()) {

            var data = {
                companyCode: this.state.code,
                companyName: this.state.name,
                companyCeo: this.state.ceoName,
                companyTurnover: parseFloat(this.state.turnOver),
                companyWebsite: this.state.website,
                stockExchange: this.state.stockExchange,
                errorMessage: ""
            };

            CompanyService.addCompany(data)
                .then(response => {
                    this.setState({
                        code: "",
                        name: "",
                        ceoName: "",
                        turnOver: "",
                        website: "",
                        stockExchange: "",
                        submitted: true,
                    });
                    console.log(response.data);
                })
                .catch(e => {
                    console.log(e);
                    if (e && e.response && e.response.status == 400) {
                        this.setState({
                            websiteError: true
                        });
                    }
                    else if (e && e.response && e.response.data && e.response.data.indexOf("Resource with specified id or name already exists.")) {
                        this.setState({
                            compCodeError: true
                        });
                    }
                    else {
                        this.setState({
                            error: true
                        });
                    }
                });
        }
    }

    render() {
        return (
            <form className="companyForm" onSubmit={this.addCompany} >
                <h2>Add Company</h2>
                {this.state.submitted ? (
                    <label style={{ color: "green" }}>Company added successfully!</label>
                ) : this.state.compCodeError ? (
                    <label style={{ color: "red" }}>Company Code already exists. Please give unique Company Code!</label>
                ) :
                    this.state.websiteError ? (
                        <label style={{ color: "red" }}>Please provide valid website!</label>
                    ) : this.state.error ? (
                        <label style={{ color: "red" }}>Error Occured!</label>
                    ) : (
                        <label ></label>
                    )}
                <div>
                    <div className="form-group">
                        <label htmlFor="companyCode">Company Code</label>
                        <input
                            type="text"
                            className="form-control"
                            id="companyCode"
                            required
                            value={this.state.code}
                            onChange={this.handleChange}
                            name="companyCode"
                        />
                        <span style={{ color: "red" }}>{this.state.errors["code"]}</span>
                    </div>
                    <div className="form-group">
                        <label htmlFor="companyName">Comany Name</label>
                        <input
                            type="text"
                            className="form-control"
                            id="companyName"
                            required
                            value={this.state.name}
                            onChange={this.handleChange}
                            name="companyName"
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="ceoName">CEO Name</label>
                        <input
                            type="text"
                            className="form-control"
                            id="ceoName"
                            required
                            value={this.state.ceoName}
                            onChange={this.handleChange}
                            name="ceoName"
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="turnOver">Turn Over</label>
                        <input
                            type="text"
                            className="form-control"
                            id="turnOver"
                            required
                            value={this.state.turnOver}
                            onChange={this.handleChange}
                            name="turnOver"
                        />
                        <span style={{ color: "red" }}>{this.state.errors["turnOver"]}</span>
                    </div>
                    <div className="form-group">
                        <label htmlFor="website">Website</label>
                        <input
                            type="text"
                            className="form-control"
                            id="website"
                            required
                            value={this.state.website}
                            onChange={this.handleChange}
                            name="website"
                        />
                        <span style={{ color: "red" }}>{this.state.errors["website"]}</span>
                    </div>
                    <div className="form-group">
                        <label htmlFor="stockExchange">Stock Exchange</label>
                        <input
                            type="text"
                            className="form-control"
                            id="stockExchange"
                            required
                            value={this.state.stockExchange}
                            onChange={this.handleChange}
                            name="stockExchange"
                        />
                    </div>
                    <button type="submit" className="btn btn-success">
                        Add
                    </button>
                </div>
            </form>
        );
    }
};