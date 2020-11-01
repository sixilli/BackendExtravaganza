import React from 'react';
import './App.css';
import './index.css';
import { HashRouter as Router, Route, Link, Switch } from 'react-router-dom'
import { Home } from './views/home';
import { News } from './views/news';
import { Frames } from './views/frames';
import { Tournaments } from './views/tournaments';
import { Resources } from './views/resources';
import { Players } from './views/players';
import { backgroundColor, theme } from './style';
import { MuiThemeProvider } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Button from '@material-ui/core/Button';
import Box from '@material-ui/core/Box';
import Container from '@material-ui/core/Container';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';



function App() {
  return (
    <MuiThemeProvider theme={theme}>
      <Router>
        <Box className="app-container">
          <AppBar
            position="static"
            color="default"
            style={{backgroundColor: backgroundColor}}
          >
            <Toolbar>
              <Typography variant="h3">TekkenHub</Typography>
              <Container>
                <Box my={4}>
                  <Button size="large" component={Link} to={"/"}>Home</Button>
                  <Button size="large" component={Link} to={"/frames"}>Frames</Button>
                  <Button size="large" component={Link} to={"/news"}>News</Button>
                  <Button size="large" component={Link} to={"/players"}>Players</Button>
                  <Button size="large" component={Link} to={"/resources"}>Resources</Button>
                  <Button size="large" component={Link} to={"/tournaments"}>Tournaments</Button>
                </Box>
              </Container>
            </Toolbar>
          </AppBar>
          <Switch>
            <Route exact path="/" component={Home} />
            <Route exact path="/news" component={News} />
            <Route exact path="/tournaments" component={Tournaments} />
            <Route exact path="/frames" component={Frames} />
            <Route exact path="/players" component={Players} />
            <Route exact path="/resources" component={Resources} />
          </Switch>
        </Box>
      </Router>
    </MuiThemeProvider>
  );
}

export default App;
