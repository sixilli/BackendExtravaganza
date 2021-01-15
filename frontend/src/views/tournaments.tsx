import React from 'react';
import { getTournaments } from '../requests';
import { DefaultLayout } from '../layouts/defaultLayout';
import { TournamentCard } from '../components/tournamentCard';
import { tournamentModel } from '../models/models';

export const Tournaments = () => {
    const [response, setResponse] = React.useState<Array<tournamentModel>>([])
    const [error, setError] = React.useState(null);
    React.useEffect(() => {
        const fetchData = async () => {
            try {
                const res = await getTournaments();
                setResponse(res.data)
            } catch (error) {
                setError(error)
            }
        };
        fetchData();
    }, []);

    if (error) {
        console.error(error);
    };

    const tournamentCards = response.map((tournament) =>
        <TournamentCard
            key={tournament.id}
            id={tournament.id}
            title={tournament.title}
            description={tournament.description}
            attendees={tournament.attendees}
            region={tournament.region}
            location={tournament.location}
            startTime={tournament.startTime}
            eventLink={tournament.eventLink}
            streamLink={tournament.streamLink}
        />
    );
    return (
    DefaultLayout(
    <div>
        <h1>Tournaments Page</h1>
        {tournamentCards}
    </div>)
    );
}


