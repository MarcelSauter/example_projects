export const planetChartData = {
  type: 'line',
  data: {
    labels: ['November', 'Dezember', 'Januar', 'Februar', 'März', 'April', 'Mai', 'Juni', 'Juli', 'August'],
    datasets: [
      {
        label: 'Goldkurs',
        data: [5.7, 14.5],
        backgroundColor: [
          'rgba(170, 170, 170, .5)', // Green
        ],
        borderColor: [
          '#aaaaaa',
        ],
        borderWidth: 2,
        pointBackgroundColor: '#aaaaaa',
        pointRadius: 4
      }
    ]
  },
  options: {
    responsive: true,
    lineTension: 1,
    legend: {
      labels: {
        fontSize: 20,
        fontColor: '#aaaaaa'
      }
    },
    scales: {
      yAxes: [{
        ticks: {
          beginAtZero: true,
          padding: 25,
        }
      }],
      xAxes: [{
        ticks: {
          fontSize: 15
        }
      }]
    }
  }
}

export default planetChartData;
