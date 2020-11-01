import React from 'react';
import Autocomplete from '@material-ui/lab/Autocomplete';
import TextField from '@material-ui/core/TextField';

interface CharacterSelectDropdownProps {
    character: string,
    characters: string[],
    changeCharacter: (character: string) => void,
}

export const CharacterSelectDropdown = (props: CharacterSelectDropdownProps) => {
    return (
        <Autocomplete
            id="combo-box-demo"
            options={props.characters.sort()}
            style={{ width: 300 }}
            renderInput={(params) => <TextField {...params} label="Character" variant="outlined" />}
            onChange={(event: any, newValue: string | null) => {
                if (newValue) {
                    props.changeCharacter(newValue);
                }
            }}
        />

    )
}