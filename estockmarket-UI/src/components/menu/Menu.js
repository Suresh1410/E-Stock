import React from "react";
import Navbar from 'react-bootstrap/Navbar'
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav'
import NavDropdown from 'react-bootstrap/NavDropdown';
import Cognizant_logo from '../../assets/cognizant.jpg';

const Menu = () => {
    return (
        <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark">
            <Container>
                
                <Navbar.Brand href="/">
                <img src={Cognizant_logo} alt="logo" />
                </Navbar.Brand>
                <Navbar.Toggle aria-controls="responsive-navbar-nav" />
                <Navbar.Collapse id="responsive-navbar-nav">
                    <Nav className="me-auto">
                        <NavDropdown title="Company" id="company-nav-dropdown">
                            <NavDropdown.Item href="/company">View Company List</NavDropdown.Item>
                            <NavDropdown.Item href="/company/register">Register Company</NavDropdown.Item>
                        </NavDropdown>
                        <NavDropdown title="Stock" id="stock-nav-dropdown">
                            <NavDropdown.Item href="/stock">View Stock List</NavDropdown.Item>
                            <NavDropdown.Item href="/stock/add">Add Stock</NavDropdown.Item>                            
                        </NavDropdown>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
}
export default Menu;