<template>
    <va-modal
        v-model="state.signupVisible"
        no-outside-dismiss
        hide-default-actions
    >
        <div class="signup-header">
            <va-button
                icon="clear"
                flat
                color="#000000"
                @click="closeSignup"
            ></va-button>
        </div>
        <div class="signup-body-modal">
            <b class="my-3">아지트에 오신 것을 환영합니다</b>
            <va-form
                style="width: 300px"
                ref="form"
                @validation="validation = $event"
            >
                <p class="my-1">아이디</p>
                <va-input
                    class="mb-4"
                    v-model="state.idValue"
                    outline
                    :rules="[
                        (value) => value.length > 0 || '필수 입력 항목입니다',
                    ]"
                />
                <p class="my-1">이름</p>
                <va-input
                    class="mb-4"
                    v-model="state.nameValue"
                    outline
                    :rules="[
                        (value) => value.length > 0 || '필수 입력 항목입니다',
                    ]"
                />
                <p class="mb-1">이메일</p>
                <va-input
                    class="mb-4"
                    v-model="state.emailValue"
                    outline
                    :rules="[
                        (value) => value.length > 0 || '필수 입력 항목입니다',
                    ]"
                />
                <p class="mb-1">비밀번호</p>
                <va-input
                    class="mb-4"
                    v-model="state.passwordValue"
                    type="password"
                    outline
                    :rules="[
                        (value) =>
                            value.length > 8 || '최소 9글자를 입력해야 합니다',
                    ]"
                />
                <p class="mb-1">비밀번호 확인</p>
                <va-input
                    class="mb-4"
                    v-model="state.passwordConfirmValue"
                    outline
                    type="password"
                    :rules="[
                        (value) =>
                            value == state.passwordValue ||
                            '비밀번호와 다릅니다',
                    ]"
                />
            </va-form>
            <div>
                <Buttons btn-text="가입하기" @click="signup"/>
            </div>
        </div>
    </va-modal>
</template>

<script>
import { computed, reactive, ref } from "vue";
import Buttons from "./Buttons.vue";
import axios from "axios";

export default {
    props: {
        open: {
            type: Boolean,
            default: false,
        },
    },
    components: {
        Buttons
    },
    setup(props, { emit }) {
        const form = ref(null);

        const state = reactive({
                signupVisible: computed(() => props.open),
                idValue: "",
                emailValue: "",
                passwordValue: "",
                passwordConfirmValue: "",
                selectValue: "",
                nameValue: "",
                validation: null,
            }),
            // signup = function () {
            //     if (form.value.validate()) {
            //         const params = {
            //             name: state.idValue,
            //             email: state.emailValue,
            //             password: state.passwordValue,
            //         }
            //         // url수정 "/api/user"
            //         axios.post('https://045d5080-b0f3-4dd5-9240-aee771955f6d.mock.pstmn.io/api/user', params)
            //          .then(function () {
            //             emit('logInId',params.name)
            //             }
            //          )
            //          .catch(err => {
            //             console.log(err)
            //          })
            //     } else {
            //         alert('다시 입력하세요')
            //     }
            // },
            closeSignup = function () {
                emit("closeSignup");
            };
        return { state, form, closeSignup };
    },
    methods: {
        async signup() {
            this.user = await this.api("/api/user", "post", {
                userId: this.state.idValue,
                name: this.state.nameValue,
                email: this.state.emailValue,
                password: this.state.passwordValue,
            });
            if (this.user) {
                this.$store.commit("logInId", this.user);
                this.closeSignup();
            } else {
                alert("아이디와 비밀번호를 확인해주세요");
            }
        },
        async api(url, method, data) {
            return (
                await axios({
                    method: method,
                    url: url,
                    data: data,
                }).catch((e) => {
                    console.log(e);
                })
            ).data;
        },
    },
};
</script>

<style>
#signup-btn {
    /* width: 100px; */
    padding: 10px;
    border-radius: 5px;

    color: #303031;
    font-weight: 700;
    font-size: 15px;
    text-align: center;
    cursor: pointer;
    transition: 0.4s;
    margin: 20px 0;
    background-color: #1a1a1b;
    color: #fff;
    display: block;
    box-sizing: border-box;
}
#signup-btn:hover {
    background-color: #fff;
    color: #000000;
}

.signup-header {
    display: flex;
    justify-content: flex-end;
}

.signup-body-modal {
    display: flex;
    flex-direction: column;
    align-items: center;
}
</style>
