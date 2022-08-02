export const convertDateToUTC = ( date) => {
    return new Date(Date.UTC(date.getUTCFullYear(),date.getUTCMonth(),
    date.getUTCDate(),date.getUTCHours(),date.getUTCMinutes(),date.getUTCSeconds())).toISOString();
}

export const randomNumber =(min, max) =>{ 
    return Math.random() * (max - min) + min;
} 