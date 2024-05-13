import { Component } from '@angular/core';

@Component({
  selector: 'app-text-converter',
  templateUrl: './text-converter.component.html',
  styleUrl: './text-converter.component.css'
})
export class TextConverterComponent {
   inputText: string = '';
  outputText: string = '';

  convertText() {
    // Add your text conversion logic here
    this.outputText = this.inputText.toUpperCase(); // For demonstration, convert input to uppercase
  }

  clearText() {
    this.inputText = '';
    this.outputText = '';
  }
}
