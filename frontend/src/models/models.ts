export interface frameDataModel {
    'Id': number,
    'Character': string,
    'Command': string,
    'HitLevel': string,
    'Damage': string,
    'StartUpFrames': string,
    'BlockFrames': string,
    'HitFrames': string,
    'CounterHitFrames': string,
    'Notes': string
};

export interface tournamentModel {
    'id': number,
    'title': string,
    'description': string,
    'attendees': number,
    'region': string,
    'location': string,
    'link': string,
    'startTime': string,
    'streamLink': string,
};