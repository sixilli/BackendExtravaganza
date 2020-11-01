import React from 'react';
import { tournamentModel } from '../models/models';
import Box from '@material-ui/core/Box';
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import CardHeader from '@material-ui/core/CardHeader';
import CardMedia from '@material-ui/core/CardMedia';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import { makeStyles, Theme, createStyles } from '@material-ui/core/styles';

const useStyles = makeStyles((theme: Theme) =>
  createStyles({
    root: {
        maxWidth: '70%',
        flexGrow: 1,
    },
    media: {
      height: 0,
      paddingTop: '56.25%', // 16:9
    },
    cardWrapper: {
        display: 'flex',
        justifyContent: 'center',
    }
  }),
);


export const TournamentCard = ({id,
    title,
    description,
    attendees,
    region,
    location,
    link,
    startTime,
    streamLink }: tournamentModel) => {
    const classes = useStyles();
    const linkHandler = (link: string) => {
        window.open(link)
    }

    return (
        <div>
            <Box paddingBottom={4} className={classes.cardWrapper}>
                <Card className={classes.root}>
                    <CardHeader
                        title={title}
                        subheader={startTime}
                    />
                    <CardMedia
                        className={classes.media}
                        image='https://www.thetech52.com/wp-content/uploads/2017/06/tekken-7-error-fix.jpg'
                        title='Tekken Image'
                    />
                    <CardContent>
                        <Typography variant="body1" component="h2">
                            {description}
                        </Typography>
                        <Typography variant="body2" component="p">
                            {attendees}
                        <br />
                            {region}
                            {location}
                        </Typography>
                    </CardContent>
                    <CardActions>
                        <Button size="small" color="primary" onClick={() => linkHandler(link)}>Event Information</Button>
                        <Button size="small" color="secondary" onClick={() => linkHandler(streamLink)}>Stream</Button>
                    </CardActions>
                </Card>
            </Box>
        </div>
    )
}