const projects = [
  {
    title: "Temperature Converter",
    file: "TemperatureConverter.java",
    description: "Converts Celsius to Fahrenheit.",
    code: `public class TemperatureConverter {
    public static void main(String[] args) {
        double celsius = 36.6;
        double fahrenheit = (celsius * 9/5) + 32;
        System.out.println("Fahrenheit: " + fahrenheit);
    }
}`
  },
  {
    title: "Grading System",
    file: "GradingSystem.java",
    description: "Grades a student based on marks.",
    code: `public class GradingSystem {
    public static void main(String[] args) {
        int marks = 85;
        if (marks >= 90) System.out.println("Grade A");
        else if (marks >= 75) System.out.println("Grade B");
        else if (marks >= 50) System.out.println("Grade C");
        else System.out.println("Fail");
    }
}`
  }
];

const container = document.getElementById('projectsContainer');

projects.forEach(project => {
  const card = document.createElement('div');
  card.className = 'card';

  card.innerHTML = `
    <h3>${project.title}</h3>
    <p>${project.description}</p>
    <pre>${project.code}</pre>
    <a class="download-btn" href="data:text/plain;charset=utf-8,${encodeURIComponent(project.code)}"
       download="${project.file}">Download Code</a>
  `;

  container.appendChild(card);
});
