import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Component, Injectable, Inject } from '@angular/core';

export class RestServiceComponent {
  private url: string;
  constructor(@Inject(Http) private http: Http) {
    this.url = 'http://localhost:8080';
  }

  public post(dataToSend: string){
    const body = JSON.stringify({ "data": dataToSend });
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Accept', 'application/json');
    console.log(body);
    this.http.post(this.url + '/print', body, { headers: headers }).subscribe();
  }

  public get(){
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Accept', 'application/json');
    console.log();
    this.http.get(this.url + '/data?text=text&url=url&image=image&style=style', { headers: headers }).subscribe(data => { console.log(data); });
  }

}
