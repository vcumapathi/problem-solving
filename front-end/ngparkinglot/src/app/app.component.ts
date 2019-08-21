import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  vehicleForm = new FormGroup({
    lot: new FormControl(),
    vehicleNumber: new FormControl(),
    parkingDuration: new FormControl(),
    parkingAmount: new FormControl()
  });

  private parkings = [];
  private error_message: string;
  private errorAlert: boolean;
  constructor(private http: HttpClient) { }
  private parkingAmount: number;

  ngOnInit() {
    this.http.get('http://localhost:8080/api/parkings').subscribe((res: any[]) => {
      console.log(res);
      this.parkings = res;
    });
  }

  calculateAmount(event: any) {
    if (event.target.value <= 60) {
      this.parkingAmount = 20;
    } else {
      this.parkingAmount = event.target.value * (0.33);
    }
    this.vehicleForm.patchValue({
      parkingAmount: Math.ceil(this.parkingAmount)
    })

  }

  onSubmit() {
    const req = this.http.post('http://localhost:8080/api/parkings',
      this.vehicleForm.value,
      {
        headers:
          { 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' }
      })
      .subscribe(
        res => {
          this.errorAlert = false;
          console.log(res);
          this.parkings.push(res);
        },
        err => {
          console.log("Error occured");
          this.errorAlert = true;
          this.error_message = err.error.details;
        }
      );
  }

}
