import Ticket from '../model/ticket'

export function getTickets(): Promise<Ticket[]> {
    return fetch("http://localhost:8080/api/v1/tickets")
        .then(response => {
            if (!response.ok) {
                throw new Error(response.statusText)
            }

            return response.text().then((json) => {
                return JSON.parse(json, dateTimeReviver)
            }) as Promise<Ticket[]>
        })
}

function dateTimeReviver(key: string, value: string) {
    const dateDetect = /(\d{4})-(\d{2})-(\d{2})T(\d{2}):(\d{2}):(\d{2})/;
    if(dateDetect.exec(value)) {
        return new Date(Date.parse(value))
    }

    return value
}