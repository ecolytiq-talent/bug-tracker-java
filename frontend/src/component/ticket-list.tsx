import React, {useEffect, useState} from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import Ticket from '../model/ticket'
import { getTickets } from '../service/ticket-service'
import {LinearProgress} from "@mui/material";

export default function TicketList() {
    const [loading, setLoading] = useState(true);
    const [tickets, setTickets] = useState<Ticket[]>([]);

    useEffect(() => {
        getTickets().then((result) => {
            setTickets(result);
            setLoading(false);
        })
    }, []);

    return (
        <TableContainer component={Paper}>
            {loading && <LinearProgress />}
            <Table sx={{ minWidth: 650 }} aria-label="simple table">
                <TableHead>
                    <TableRow>
                        <TableCell>Id</TableCell>
                        <TableCell>Title</TableCell>
                        <TableCell>Status</TableCell>
                        <TableCell>Created</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {tickets.map((ticket) => (
                        <TableRow key={ticket.id}
                            sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                        >
                            <TableCell>{ticket.id}</TableCell>
                            <TableCell>{ticket.title}</TableCell>
                            <TableCell>{ticket.status}</TableCell>
                            <TableCell>{ticket.created_at.toLocaleDateString()} {ticket.created_at.toLocaleTimeString()}</TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    );
}