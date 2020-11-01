import React from 'react';
//import { getTournaments } from '../requests';
import { DefaultLayout } from '../layouts/defaultLayout';
import { TournamentCard } from '../components/tournamentCard';
//import { tournamentModel } from '../models/models';

export const Tournaments = () => {
    //const [response, setResponse] = React.useState<Array<tournamentModel>>([])
    //const [error, setError] = React.useState(null);
    //React.useEffect(() => {
        //const fetchData = async () => {
            //try {
                //const res = await getTournaments();
                //setResponse(res.data)
            //} catch (error) {
                //setError(error)
            //}
        //};
        //fetchData();
    //})
    let response = [
        {
            "id": 1,
            "title": "Tekken World Tour!",
            "description": "King of the iron fist tournament",
            "attendees": 64,
            "region": "NA lul",
            "location": "Los Angeles, California",
            "link": "https://TekkenHub.com",
            "startTime": "2020-10-21 20:16:40",
            "created_at": "2020-10-21T20:16:40.000000Z",
            "updated_at": "2020-10-21T20:16:40.000000Z",
            "streamLink": "https://twitch.tv"
        },
        {
            "id": 2,
            "title": "Evo Japan",
            "description": "Arslan please have mercy",
            "attendees": 128,
            "region": "Asia",
            "location": "Tokyo, Japan",
            "link": "https://TekkenHub.com",
            "startTime": "2020-10-27 20:16:40",
            "created_at": "2020-10-27T17:08:43.000000Z",
            "updated_at": "2020-10-27T17:08:43.000000Z",
            "streamLink": "https://twitch.tv"
        }
    ]

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
            link={tournament.link}
            streamLink={tournament.streamLink}
        />
    );
    return (
    DefaultLayout(
    <div>
        <h1>Tournaments Page</h1>
        {tournamentCards}
    </div>)
    )
}


