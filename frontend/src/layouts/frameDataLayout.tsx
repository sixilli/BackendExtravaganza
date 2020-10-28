
import React from 'react';
import Box from '@material-ui/core/Box';
import Container from '@material-ui/core/Container';

export const FrameDataLayout = (content: JSX.Element) => {
    return (
    <Box flexGrow={1}>
        <Container maxWidth={'xl'}>
            { content }
        </Container>
    </Box>
    )
}