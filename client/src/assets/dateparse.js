function elapsedTime_ago(date) {
  // 시간 데이터가 들어오면 현재 시간과 비교하여 경과된 시간을 리턴하는 함수
  const uploaded = new Date(date);
  const current = new Date();
  const diff = (current - uploaded) / 1000; // <- 초단위로 변환한 시간

  const unit = [
    { singular: "day", plural: "days", seconds: 60 * 60 * 24 },
    { singular: "hour", plural: "hours", seconds: 60 * 60 },
    { singular: "min", plural: "mins", seconds: 60 },
    { singular: "sec", plural: "secs", seconds: 1 },
  ];

  for (let value of unit) {
    const betweenTime = Math.floor(diff / value.seconds);

    if (betweenTime > 1) {
      if (value.singular === "day") {
        const options = {
          year: "numeric",
          month: "short",
          day: "numeric",
          hour12: false,
          hour: "2-digit",
          minute: "2-digit",
        };

        let converted = uploaded.toLocaleDateString("en-US", options).split("");
        converted.splice(-7, 1, " at");

        return converted.join("");
      }
      return `${betweenTime} ${value.plural} ago`;
    } else if (betweenTime === 1) {
      if (value.singular === "day") return "yesterday";
      return `${betweenTime} ${value.singular} ago`;
    }
  }
}

export default elapsedTime_ago;
