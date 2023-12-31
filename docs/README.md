# 📜 기능목록

⚙️ 고객의 정보를 관리하는 기능

- [ ]  방문날짜 정보를 저장하는 기능
- [ ]  주문 메뉴들과 개수를 저장하는 기능
- [ ]  이벤트 주의사항을 지켰는지 관리하는 기능
- [ ]  어떤 할인을 받았는지 관리하는 기능
- [ ]  이벤트 뱃지를 관리하는 기능

---

## 입력

⚙️ 고객님들의 식당 방문 날짜를 입력받는 기능

- [x]  숫자만 입력가능하도록 한다.
- [x]  1~31의 숫자만 입력가능하도록 한다.
    - [x]  "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."라는 에러 메시지출력한다.

⚙️ 주문하실 메뉴와 개수를 입력받는 기능

- [x]  메뉴들을 분류하는 기능
    - [x]  분류한 메뉴들 중 중복된 메뉴가 없는지 검증해요
    - [x]  "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요." 를 보여준다.
- [x]  분류한 메뉴로부터 메뉴와 개수를 분류하는 기능
    - [x]  고객님이 메뉴판에 없는 메뉴를 입력하는 경우,
      "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."라는 에러 메시지를 보여준다.
    - [x]  메뉴의 개수는 1 이상의 숫자만 입력되도록 한다.
      이외의 입력값은 "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."라는
      에러 메시지를 보여 주세요.


---

⚙️ 이벤트 주의사항 기능

- [ ]  총주문금액에 10,000원 이상일때 이벤트가 적용된다.
    - [ ]  총 주문금액을 계산하는 기능
- [ ]  음료만 주문 시, 주문할 수 없다.
    - [ ]  주문 메뉴 중 음료만 주문했는지 확인하는 기능
    - [ ]  메뉴와 개수를 다시 입력받는다.
- [ ]  메뉴는 한 번에 최대 20개까지 주문 가능하다.
    - [ ]  메뉴 총 개수가 20개인지 확인하는 기능
    - [ ]  메뉴와 개수를 다시 입력받는다.

⚙️ 할인을 관리하는 기능

- [x]  일자에 해당하는 요일을 구하는 기능
- [x]  크리스마스 디데이 할인 기능
    - [x]  1000원으로 시작하고 크리스마스가 다가올수록 100원씩 증가하는 기능
    - 이벤트 기간은 1일부터 25일로 한정한다.
- [x]  평일 할인 기능
    - [x]  일요일부터 목요일까지는 디저트 메뉴를 1개당 2023원씩 할인하는 기능
- [x]  주말 할인 기능
    - [x]  금요일과 토요일에는 메인 메뉴 1개당 2023원씩 할인하는 기능
- [x]  특별 할인 기능
    - [x]  이벤트 달력에 별이 있으면 (3, 10, 17, 24, 25, 31) 총 주문금액에서 1,000원을 할인
- [x]  증정 이벤트 기능
    - [x]  할인 전 총 주문금액이 12만원 이상일 경우 샴페인 1을 증정
- [x]  총 혜택 금액을 구하는 기능
    - [x]  증정 이벤트에 당첨되었다면 샴페인 수만큼 혜택 내역에 반영한다.

⚙️ 이벤트 뱃지를 부여하는 기능

- [x]  금액에 해당하는 뱃지를 부여하는 기능
- [x]  5원 이상 ⭐, 1만원 이상 🎄, 2만원 이상 🧑‍🎄
- [x]  이벤트 뱃지가 부여되지 않는 경우, “없음”을 출력

⚙️ 금액에 관리하는 기능

- [x]  할인 전 총주문 금액을 계산하는 기능
- [x]  총 혜택 금액을 구하는 기능
- [x]  할인 후 예상 결제 금액을 계산하는 기능

---

## 출력

- [x]  플래너의 인사 출력 기능
- [x]  일자를 입력을 알리는 기능
- [x]  주문 메뉴와 개수를 입력을 알리는 기능
- [x]  `12월 xx일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!` 출력 기능
- [x]  주문받은 메뉴와 개수를 출력하는 기능
- [x]  할인 전 총주문 금액을 출력하는 기능
- [x]  혜택 내역을 출력하는 기능
- [x]  총혜택 금액을 출력하는 기능
- [x]  할인 후 예상 결제 금액을 출력하는 기능
- [x]  12월 이벤트 배지를 출력하는 기능

---