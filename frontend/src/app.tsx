import React from 'react';
import logo from './ecolytiq.svg';
import Container from '@mui/material/Container';
import TicketList from './component/ticket-list';
import './app.css';

function App() {
    return (
        <div className="App">
            <header className="App-header">
                <img src={logo} className="App-logo" alt="logo" />
                <p>Welcome to ecolytiq!</p>
            </header>
            <Container maxWidth="lg" sx={{paddingTop: '2rem'}}>
                <h1>Bug tracker</h1>
                <TicketList/>
            </Container>
        </div>
    );
}

export default App;
